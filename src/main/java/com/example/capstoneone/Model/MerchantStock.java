package com.example.capstoneone.Model;

import jakarta.validation.constraints.*;
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

    @NotNull
    @Positive
    @Max(10)
    private int quantityForContentCreator;//يحدد التاجر اثناء انشاء ميرشنت ستوك كم عدد القطع اللي يبي يوزعها بالمجان على صناع المحتوى
}
