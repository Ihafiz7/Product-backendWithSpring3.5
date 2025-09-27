package com.example.AngularApi.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private  Integer quantity;
    private Boolean available;
    private String supplierEmail;
    private LocalDate manufacturingDate;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ElementCollection
    private List<String> tags;

    @Lob
    private String description;

    @Column(columnDefinition = "Text")
    private String longDesc;



    public enum Category{
        ELECTRONICS,CLOTHING,BOOKS,FOOD,OTHER
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

}
