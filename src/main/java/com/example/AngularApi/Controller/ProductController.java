package com.example.AngularApi.Controller;

import com.example.AngularApi.Entity.Product;
import com.example.AngularApi.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")

public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = service.create(product);
        return ResponseEntity.ok(created);
    }

    // READ - GET ALL
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = service.getAll();
        return ResponseEntity.ok(products);
    }

    // READ - GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return service.findbyId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            Product updated = service.update(id, product);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
