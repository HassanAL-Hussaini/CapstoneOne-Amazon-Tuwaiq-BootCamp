package com.example.capstoneone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @NotEmpty(message = "Product ID cannot be empty. Please provide a valid ID.")
    private String id;

    @NotEmpty(message = "Product name cannot be empty.")
    @Size(min = 4, message = "Product name must be at least 4 characters long.")
    private String name;

    @Positive(message = "Product price must be a positive number.")
    private double price;

    @NotNull(message = "Category ID must not be null. Please assign a valid category.")
    //category is object so use notNull with it.
    private CategoryId categoryId;//add Object of name and id;
}
