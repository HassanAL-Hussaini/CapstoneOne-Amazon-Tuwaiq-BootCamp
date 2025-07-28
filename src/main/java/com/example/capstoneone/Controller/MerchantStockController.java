package com.example.capstoneone.Controller;

import com.example.capstoneone.API.ApiResponse;
import com.example.capstoneone.Model.MerchantStock;
import com.example.capstoneone.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/merchant-stock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    // Get all merchant stocks
    @GetMapping("/get")
    public ArrayList<MerchantStock> getAllStocks() {
        return merchantStockService.getStockMerchants();
    }

    // Add new merchant stock
    @PostMapping("/add")
    public ResponseEntity<?> addStock(@RequestBody @Valid MerchantStock stock) {
        boolean isSuccess  = merchantStockService.addStockMerchant(stock);
        if(isSuccess){
            return ResponseEntity.status(200).body("added Successfully");
        }
        return ResponseEntity.status(400).body("Invalid input,merchant or product dose not found in the system");
    }

    // Update existing merchant stock by ID
    @PutMapping("/update/{id}")
    public String updateStock(@PathVariable String id, @RequestBody @Valid MerchantStock updatedStock) {
        boolean isUpdated = merchantStockService.updateStockMerchant(id, updatedStock);
        return isUpdated ? "Stock updated successfully" : "Stock not found";
    }

    // Delete stock by ID
    @DeleteMapping("/delete/{id}")
    public String deleteStock(@PathVariable String id) {
        boolean isDeleted = merchantStockService.deleteStockMerchant(id);
        return isDeleted ? "Stock deleted successfully" : "Stock not found";
    }

    // Q 11
    @PutMapping("/increase-stock/{merchantId}/{productId}/{stock}")
    public ResponseEntity<?> increaseStock(@PathVariable String merchantId ,
                                               @PathVariable String productId ,
                                               @PathVariable int stock){
        boolean isIncreases = merchantStockService.increaseStock(merchantId,productId,stock);
        if(isIncreases){
            return ResponseEntity.status(200).body(new ApiResponse("stock increases Successfully"));
        }else{
            return ResponseEntity.status(400).body(new ApiResponse("stock dose Not increases Successfully"));
        }
    }

    //Q12
    @PutMapping("/buy-product/{userId}/{merchantId}/{productId}")
    public ResponseEntity<?> buyProduct(@PathVariable String userId ,@PathVariable String merchantId ,@PathVariable String productId){
        ApiResponse message =  merchantStockService.payment(userId,merchantId,productId);
        if(message.getStatus()==200){
            return ResponseEntity.ok().body(message.getMessage());
        }
        return ResponseEntity.badRequest().body(message.getMessage());
    }
    //Extra Endpoint get more than 1000 product stock , Note as I said in the service I will Count this method from 5 extra it's only for clarification
    @GetMapping("/get-1000-stock")
    public ResponseEntity<?> getProductWith1000StockOrMore(){
        if(merchantStockService.getStockMoreThan1000() != null){
            return ResponseEntity.ok().body(merchantStockService.getStockMoreThan1000());
        }
        return ResponseEntity.badRequest().body(new ApiResponse("Array is empty"));
    }

    //String wholesaleBuyerId ,String productId, int numberOfProduct
    @PutMapping("/wholesale-payment/{wholesaleBuyerId}/{productId}/{numberOfProduct}")
    public ResponseEntity<?> wholeSaleBuyerPayment(@PathVariable String wholesaleBuyerId ,@PathVariable String productId,@PathVariable int numberOfProduct){
        ApiResponse message = merchantStockService.wholeSaleBuyerPayment(wholesaleBuyerId , productId, numberOfProduct);
        if(message.getStatus() == 200){
            return ResponseEntity.ok().body(message.getMessage());
        }
        return ResponseEntity.badRequest().body(message.getMessage());

    }
}
