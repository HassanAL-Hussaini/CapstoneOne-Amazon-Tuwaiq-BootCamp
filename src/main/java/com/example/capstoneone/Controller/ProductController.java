package com.example.capstoneone.Controller;

import com.example.capstoneone.API.ApiResponse;
import com.example.capstoneone.Model.Product;
import com.example.capstoneone.Service.MerchantStockService;
import com.example.capstoneone.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final HandlerMapping resourceHandlerMapping;
    private final MerchantStockService merchantStockService;

    // Get all products
    @GetMapping("/get")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    // Add a new product
    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody @Valid Product product, Errors errors) {
        if(errors.hasErrors()){
            ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        if (productService.findById(product.getId()) != null) {
            return ResponseEntity.status(400).body("Product with the same ID already exists.");
        }//امنع تكرار ال ID

        // check if category exists before adding
        if (productService.updateProduct(product.getId(), product)) {
            return ResponseEntity.ok("Product updated (because it already existed).");
        } else {
            productService.addProduct(product);
            return ResponseEntity.ok("Product added successfully.");
        }
    }
    // Update a product
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable String id, @RequestBody @Valid Product product, Errors errors) {
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }

        boolean updated = productService.updateProduct(id, product);
        if (updated) {
            return ResponseEntity.ok("Product updated successfully.");
        }
        return ResponseEntity.status(404).body("Product or category not found.");
    }
    // Delete a product
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.ok("Product deleted successfully.");
        }
        return ResponseEntity.status(404).body("Product not found.");
    }
    // Get product by ID
    @GetMapping("get-product-by-id/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        Product product = productService.findById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(404).body("Product not found");
    }
    @GetMapping("api/get-product-criticism/{id}")//TODO جيب المنتج id واعرض الكومنت حقه فقط
    public ResponseEntity<?> getProductCriticism(@PathVariable String id){
         Product details = productService.getProductCriticism(id);
         return ResponseEntity.status(202).body(details);
    }

    @PutMapping("/content-creator-request/{contentCreatorId}/{productId}")
    public ResponseEntity<?> requestContentCreatorItem(@PathVariable String contentCreatorId,
                                                       @PathVariable String productId) {
        ApiResponse response = merchantStockService.permissionToContentCreator(contentCreatorId, productId);

        return ResponseEntity.status(response.getStatus()).body(response.getMessage());
    }

}