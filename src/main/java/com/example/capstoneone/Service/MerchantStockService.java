package com.example.capstoneone.Service;

import com.example.capstoneone.API.ApiResponse;
import com.example.capstoneone.Model.Merchant;
import com.example.capstoneone.Model.MerchantStock;
import com.example.capstoneone.Model.Product;
import com.example.capstoneone.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private final MerchantService merchantService;
    private final ProductService productService;
    private final UserService userService;


    ArrayList<MerchantStock> merchantsStock = new ArrayList<>();

    public ArrayList<MerchantStock> getStockMerchants() {
        return merchantsStock;
    }

    public boolean addStockMerchant(MerchantStock merchantStock) {
        if(merchantStock.getStock() < 10){
            return false;
        }
        Merchant merchant = merchantService.findById(merchantStock.getMerchantId());
        Product product = productService.findById(merchantStock.getProductId());

        if (merchant == null || product == null) {
            return false;
        }
        merchantsStock.add(merchantStock);
        return true;
    }

    public boolean updateStockMerchant(String id, MerchantStock updatedMerchantStock) {
        for (int i = 0; i < merchantsStock.size(); i++) {
            if (merchantsStock.get(i).getId().equals(id)) {
                merchantsStock.set(i, updatedMerchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStockMerchant(String id) {
        for (int i = 0; i < merchantsStock.size(); i++) {
            if (merchantsStock.get(i).getId().equals(id)) {
                merchantsStock.remove(i);
                return true;
            }
        }
        return false;
    }

    public MerchantStock findByProductId(String productId){
        for (MerchantStock merchantStock :merchantsStock){
            if(merchantStock.getProductId().equals(productId)){
                return merchantStock;
            }
        }
        return null;
    }

    //After CRUD 11 and 12

    //----------Q11----------
    public boolean increaseStock(String merchantId , String productId , int stock){
        Merchant merchant = merchantService.findById(merchantId);
        Product product = productService.findById(productId);

        if (merchant == null || product == null) {
            return false;
        }

            for (MerchantStock merchantStock : merchantsStock){
                if(merchantStock.getMerchantId().equals(merchantId) &&
                    merchantStock.getProductId().equals(productId)){
                    merchantStock.setStock(merchantStock.getStock() + stock);
                    return true;
                }
            }
            return false;
    }
    // --------Q12---------
    //DONE االافضل انك ترجع سترنق وتهندلها في الكونترولر على حسب السترنق
    public String payment(String userId , String merchantId , String productId){
        User user = userService.getUserById(userId);
        Merchant merchant = merchantService.findById(merchantId);
        Product product = productService.findById(productId);
        if(user == null || merchant == null || product == null){
            return "you have to add (user / merchant / product) before payment ";//400
        }
        if(!checkStockQuantity(productId)){
            return "out of stock";//400
        }

        if(user.getBalance() >= product.getPrice()){
            user.setBalance(user.getBalance() - product.getPrice());
            MerchantStock merchantStock = findAvailableStockByMerchantStockId(productId);
            merchantStock.setStock(merchantStock.getStock() - 1);
            return "Payment Done Successfully";
        }
            return "payment Rejected ,money Not enough ";
    }

    //Extra services
    //ترجع boolean بناء على الstock اذا فيه امكانية للشراء ولا لا
    public boolean checkStockQuantity(String productId){
        for (MerchantStock merchantStock: merchantsStock){
            if(merchantStock.getProductId().equals(productId)){
                if(merchantStock.getStock() > 0 ){
                    return true;
                }
            }
        }
        return false;
    }

    public MerchantStock  findAvailableStockByMerchantStockId(String MerchantStockId){
        for (MerchantStock merchantStock: merchantsStock){
            if(merchantStock.getProductId().equals(MerchantStockId)){
                if(merchantStock.getStock() > 0 ){
                    return merchantStock;
                }
            }
        }
        return null;
    }

    public ArrayList<?> getStockMoreThan1000(){//Basic Endpoint Not from the 5 Extra it's only for clarification.
            ArrayList<MerchantStock> stockMoreThan1000 = new ArrayList<>();
        for (MerchantStock merchantStock : merchantsStock){
            if(merchantStock.getStock() >= 1000){
                stockMoreThan1000.add(merchantStock);
            }
        }
        return stockMoreThan1000;
    }

//البيع بالجملة عن طريق هذي الميثود
    public ApiResponse wholeSaleBuyerPayment(String wholesaleBuyerId ,String productId, int numberOfProduct){
        MerchantStock productMerchantStock = findByProductId(productId);
        User user = userService.getUserById(wholesaleBuyerId);
        if(user == null || productMerchantStock == null){
            return new ApiResponse("user ID Incorrect",400);
        }
        if(!user.getRole().equals("Wholesale buyer")){
            return new ApiResponse("you Don't have permeation Access WholeSale Buyer Role : ",400);
        }
        if(numberOfProduct < 500){//اخليها ما يقدر يشتري اقل من 500
            return new ApiResponse("the Quantity must be more than 500",400);
        }
        if(numberOfProduct > productMerchantStock.getStock()){
            return new ApiResponse("your Order is bigger then the Stock Quantity, we Only have : "+productMerchantStock.getStock(),400);
        }
        //المبلغ بدون خصم
        double amount = numberOfProduct*(productService.findById(productId).getPrice());

        if(numberOfProduct > 2000){
            amount = amount*0.9; // خصم 10 بالميه للمشترين بأكثر من 2000 قطعه
        }else{
            amount*=0.95;//خصم 5 بالميه للي اشتروا اقل من 2000 قطعه
        }
         if(! (user.getBalance() >= amount)){
             return new ApiResponse("you dont have enough money + the Total is "+amount+"and you only have "+user.getBalance(),400);
         }
         //payment and decrees from stock :
        user.setBalance(user.getBalance() - amount);
        productMerchantStock.setStock(productMerchantStock.getStock()-numberOfProduct);
        return new ApiResponse("the product Done successfully",200);
    }

    public ApiResponse permissionToContentCreator(String contentCreatorId,String productId){
        User user = userService.getUserById(contentCreatorId);
        MerchantStock merchantStock = findByProductId(productId);
        Product product = productService.findById(productId);
        if(user == null || !(user.getRole().equals("content creator"))){
            return new ApiResponse("you are not Content Creator",400);
        }
        if(merchantStock == null || !(merchantStock.getQuantityForContentCreator() > 0)){
            return new ApiResponse("\"The items for content creators are no longer available. We apologize — the stock has run out." ,400);
        }
        if(!product.isContentCreatorProduct()){
            return new ApiResponse("this product is no longer available yet for Content Creator, the Merchant Stop the Service",400);
        }
        if(user.isTakingTheContentCreatorGift()){
            return new ApiResponse("you Already Taking One, you can't take 2 pieces",400);
        }
        merchantStock.setQuantityForContentCreator(merchantStock.getQuantityForContentCreator() - 1);
        user.setTakingTheContentCreatorGift(true);//عشان ما يكون عنده القدره يطلب القطعه مره اخرى
        return new ApiResponse("the Limited Edition for Content Creator arriving you soon at your Current Location",200);
    }
}
