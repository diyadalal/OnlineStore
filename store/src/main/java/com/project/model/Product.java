package com.project.model;

import java.math.BigDecimal;

public class Product {
    private int productId;
    private String name;
    private Category category;
    private String description;
    private String brand;
    private BigDecimal price;
    private boolean sold;

    public Product() {}

    public Product(int productId, String name, Category category, String description, String brand, BigDecimal price, boolean sold) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.sold = sold;
    }

    // Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Category getCategory() { return category; }
    public Category setCategory() { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public boolean isSold() { return sold; }
    public void setSold(boolean sold) {this.sold = sold; }
}
