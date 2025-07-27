package com.example.capstoneone.Service;

import com.example.capstoneone.Model.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService{
    ArrayList<Merchant> merchants = new ArrayList<>();

    public ArrayList<Merchant> getMerchants() {
        return merchants;
    }

    public void addMerchant(Merchant merchant) {
        merchants.add(merchant);
    }

    public boolean updateMerchant(String id, Merchant updatedMerchant) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId().equals(id)) {
                merchants.set(i, updatedMerchant);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchant(String id) {
        return merchants.removeIf(merchant -> merchant.getId().equals(id));
    }

    public Merchant findById(String id) {
        for (Merchant merchant : merchants) {
            if (merchant.getId().equals(id)) {
                return merchant;
            }
        }
        return null;
    }
}
