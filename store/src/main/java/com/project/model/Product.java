package com.project.model;

import com.project.enums.Category;

import java.math.BigDecimal;

public class Product {
    private int productId;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private boolean sold;
    private Category category;

    public Product() {}

    public Product(int productId, String name, String description, String brand, BigDecimal price, boolean sold) {
        this.productId = productId;
        this.name = name;
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
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setSold(boolean sold) {  this.sold = true; }
    public boolean isSold() { return sold; }
    public void setCategory(Category category) { this.category = category; }
    public Category getCategory() { return category; }
}
