package com.project.model;
import com.project.enums.Color;
import com.project.enums.Size;

public class ProductVariant {
    private int variantId;
    private Integer productId; // Nullable in SQL
    private Size size;   // Mapped to the Size Enum
    private Color color; // Mapped to the Color Enum
    private Integer stock; // Nullable in SQL

    public ProductVariant() {}

    public ProductVariant(int variantId, Integer productId, Size size, Color color, Integer stock) {
        this.variantId = variantId;
        this.productId = productId;
        this.size = size;
        this.color = color;
        this.stock = stock;
    }

    // Getters and Setters
    public int getVariantId() { return variantId; }
    public void setVariantId(int variantId) { this.variantId = variantId; }
    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }
    public Size getSize() { return size; }
    public void setSize(Size size) { this.size = size; }
    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}
