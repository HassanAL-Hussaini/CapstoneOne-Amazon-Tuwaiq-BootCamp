package com.example.capstoneone.API;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ApiResponse {

    private String message;
    private int status = 0 ;
    public ApiResponse(String message){
        this.message = message;
        this.status = 0 ;
    }
}
