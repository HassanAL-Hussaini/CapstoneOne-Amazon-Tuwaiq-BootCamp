package com.example.capstoneone.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotEmpty(message = "ID cannot be empty. Please provide a valid ID.")
    private String id;

    @NotEmpty(message = "Product ID cannot be empty. Please provide the associated product ID.")
    private String productId;

    @NotEmpty(message = "Merchant ID cannot be empty. Please provide the associated merchant ID.")
    private String merchantId;

    @NotNull(message = "Stock cannot be null. Please enter an initial stock quantity.")
    private int stock;

}
