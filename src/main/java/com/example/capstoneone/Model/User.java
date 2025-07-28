package com.example.capstoneone.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "ID must not be empty")
    private String id;

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 6, message = "Username must be more than 5 characters")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 7, message = "Password must be more than 6 characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$",
            message = "Password must contain both letters and digits")
    private String password;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email must be valid")
    private String email;

    @NotEmpty(message = "Role must not be empty")
    @Pattern(regexp = "^(Admin|Customer|Interester|Wholesale buyer)$", message = "Role must be either Admin, Customer, Interester, Wholesale buyer")
    private String role;

    @NotNull(message = "Balance must not be null")
    @Positive(message = "Balance must be positive")
    private Double balance;

    @NotEmpty
    private String preference;

    @NotEmpty
    @Pattern(regexp = "^(public|private)$", message = "account must be private or public")
    private String account;


}
