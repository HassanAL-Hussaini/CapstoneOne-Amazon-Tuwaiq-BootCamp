package com.example.capstoneone.Controller;

import com.example.capstoneone.Model.MerchantStock;
import com.example.capstoneone.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public String addStock(@RequestBody @Valid MerchantStock stock) {
        merchantStockService.addStockMerchant(stock);
        return "Merchant stock added successfully";
    }

    // Update existing merchant stock by ID
    @PutMapping("/{id}")
    public String updateStock(@PathVariable String id, @RequestBody @Valid MerchantStock updatedStock) {
        boolean isUpdated = merchantStockService.updateStockMerchant(id, updatedStock);
        return isUpdated ? "Stock updated successfully" : "Stock not found";
    }

    // Delete stock by ID
    @DeleteMapping("update/{id}")
    public String deleteStock(@PathVariable String id) {
        boolean isDeleted = merchantStockService.deleteStockMerchant(id);
        return isDeleted ? "Stock deleted successfully" : "Stock not found";
    }
}
