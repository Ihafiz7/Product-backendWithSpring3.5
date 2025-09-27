package com.example.AngularApi.Service;

import com.example.AngularApi.Entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductApiService {
    private final WebClient webClient;

    // Get all products
    public List<Product> getAllProducts(Boolean status) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("status", status).build())
                .retrieve()
                .bodyToFlux(Product.class)
                .collectList()
                .block();
    }

    // Get product by ID
    public Product getProductById(Long id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    // Create new product
    public Product createProduct(Product product) {
        return webClient.post()
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    // Update product
    public Product updateProduct(Long id, Product product) {
        return webClient.put()
                .uri("/{id}", id)
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    // Delete product
    public void deleteProduct(Long id) {
        webClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
