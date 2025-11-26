package com.project.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
    private int productId;
    private Integer categoryId; // Nullable in SQL
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private boolean sold;

    public Product() {}

    public Product(int productId, Integer categoryId, String name, String description, String brand, BigDecimal price, LocalDateTime createdAt, boolean sold) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.createdAt = createdAt;
        this.sold = sold;
    }

    // Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public boolean isSold() { return sold; }
    public void setSold(boolean sold) { this.sold = sold; }
}
