package com.example.capstoneone.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {


    //DONE webCLint Spring boot search on it
    //After search it's new version of restTemplate, better to use it instead of restTemplate.
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}

