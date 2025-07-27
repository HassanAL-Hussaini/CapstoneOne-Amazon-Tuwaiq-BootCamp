package com.example.capstoneone.Service;

import com.example.capstoneone.Model.Merchant;
import com.example.capstoneone.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {
    ArrayList<MerchantStock> merchantsStock = new ArrayList<>();

    public ArrayList<MerchantStock> getStockMerchants() {
        return merchantsStock;
    }

    public void addStockMerchant(MerchantStock merchant) {
        merchantsStock.add(merchant);
    }

    public boolean updateStockMerchant(String id, MerchantStock updatedMerchant) {
        for (int i = 0; i < merchantsStock.size(); i++) {
            if (merchantsStock.get(i).getId().equals(id)) {
                merchantsStock.set(i, updatedMerchant);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStockMerchant(String id) {
        return merchantsStock.removeIf(merchant -> merchant.getId().equals(id));
    }

    public MerchantStock findById(String id) {
        for (MerchantStock merchant : merchantsStock) {
            if (merchant.getId().equals(id)) {
                return merchant;
            }
        }
        return null;
    }
}
