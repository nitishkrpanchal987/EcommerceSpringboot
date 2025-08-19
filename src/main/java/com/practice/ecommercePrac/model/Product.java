package com.practice.ecommercePrac.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private BigDecimal price;
    private int inventory;
    private String brand;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    public Product(String name, BigDecimal price, int inventory, String brand, String description, Category category) {
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.brand = brand;
        this.description = description;
        this.category = category;
    }
}
