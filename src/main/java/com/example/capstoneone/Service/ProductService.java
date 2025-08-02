package com.example.capstoneone.Service;

import com.example.capstoneone.Model.CategoryId;
import com.example.capstoneone.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    //Rating Api
    private final String API_URL = "https://jsonplaceholder.typicode.com/comments";
    @Autowired
    private RestTemplate restTemplate;

    private final ArrayList<Product> products = new ArrayList<>();
    private final CategoryIdService categoryIdService;

    public ArrayList<Product> getAllProducts() {
        return products;
    }

    public void addProduct(Product product) {
        String productCategoryId = product.getCategoryId().getId();
        String categoryId = categoryIdService.findById(productCategoryId).getId();
        if (categoryId.equals(productCategoryId)) {
            products.add(product);
        }

    }

    public boolean updateProduct(String id, Product updatedProduct) {
        String productCategoryId = updatedProduct.getCategoryId().getId();
        String categoryId = categoryIdService.findById(productCategoryId).getId();
        if (categoryId.equals(productCategoryId)) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(id)) {
                    products.set(i, updatedProduct);
                    return true;
                }
            }
        }
        return false; // if the product id found and the category id is already exist in the category List update the item.
    }

    public boolean deleteProduct(String id) {
        return products.removeIf(product -> product.getId().equals(id));
    }

    public Product findById(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    public Product getProductCriticism(String productID) {
        try {
            Product[] comments = restTemplate.getForObject(API_URL, Product[].class);
            if (comments != null) {
                for (Product comment : comments) {
                    if (comment.getId().equals(productID)) {
                        return comment; // يرجع الكائن كامل
                    }
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }
}
