package com.example.capstoneone.Controller;


import com.example.capstoneone.Model.Merchant;
import com.example.capstoneone.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ArrayList<Merchant> getMerchants() {
        return merchantService.getMerchants();
    }

    @PostMapping("/add")
    public String addMerchant(@RequestBody @Valid Merchant merchant) {
        merchantService.addMerchant(merchant);
        return "Merchant added successfully";
    }

    @PutMapping("update/{id}")
    public String updateMerchant(@PathVariable String id, @RequestBody @Valid Merchant merchant) {
        boolean updated = merchantService.updateMerchant(id, merchant);
        return updated ? "Merchant updated successfully" : "Merchant not found";
    }

    @DeleteMapping("delete/{id}")
    public String deleteMerchant(@PathVariable String id) {
        boolean deleted = merchantService.deleteMerchant(id);
        return deleted ? "Merchant deleted successfully" : "Merchant not found";
    }
}