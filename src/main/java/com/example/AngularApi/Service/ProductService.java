package com.example.AngularApi.Service;

import com.example.AngularApi.Entity.Product;
import com.example.AngularApi.Repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Optional<Product> findbyId(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Product update(Long id, Product product) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with ID " + id + " not found."));
//        Product oldP = opPro.get();
//
//        oldP.setName(product.getName());
//        oldP.setPrice(product.getPrice());
//        oldP.setQuantity(product.getQuantity());
//        oldP.setAvailable(product.getAvailable());
//        oldP.setSupplierEmail(product.getSupplierEmail());
//        oldP.setManufacturingDate(product.getManufacturingDate());
//        oldP.setCategory(product.getCategory());
//        oldP.setTags(product.getTags());
//        oldP.setDescription(product.getDescription());
//        oldP.setLongDesc(product.getLongDesc());
//        return repository.save(oldP);
        BeanUtils.copyProperties(product, existing, getNullPropertyNames(product));
        return repository.save(existing);
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        return Arrays.stream(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

}
