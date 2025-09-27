package com.example.AngularApi.Controller;

import com.example.AngularApi.Entity.Product;
import com.example.AngularApi.Service.ProductApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test/products")
@RequiredArgsConstructor
public class ProductTestController {
    private final ProductApiService productApiClient;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) Boolean status) {
        List<Product> products = productApiClient.getAllProducts(status);
        return ResponseEntity.ok(products);
    }

    // Get a product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productApiClient.getProductById(id);
        if (product != null) return ResponseEntity.ok(product);
        else return ResponseEntity.notFound().build();
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = productApiClient.createProduct(product);
        return ResponseEntity.ok(created);
    }

    // Update product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updated = productApiClient.updateProduct(id, product);
        if (updated != null) return ResponseEntity.ok(updated);
        else return ResponseEntity.notFound().build();
    }

    // Delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productApiClient.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
