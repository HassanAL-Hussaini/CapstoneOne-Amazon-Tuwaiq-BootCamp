package com.example.capstoneone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {
    @NotEmpty(message = "ID cannot be empty. Please provide a valid ID.")
    private String id;

    @NotEmpty(message = "Name cannot be empty. Please enter a name.")
    @Size(min = 4, message = "Name must be at least 4 characters long.")
    private String name;

}
