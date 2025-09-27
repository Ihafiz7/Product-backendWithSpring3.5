package com.example.AngularApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ProductClient {
    @Bean
    public WebClient WebClient(){
        return WebClient.builder()
                .baseUrl("http://192.168.20.20:8080/api/products")
                .build();
    }
}
