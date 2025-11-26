package com.project.model;

import java.time.LocalDateTime;

public class CartItem {
    private int customerId;
    private int variantId;
    private LocalDateTime createdAt;

    public CartItem() {}

    public CartItem(int customerId, int variantId, LocalDateTime createdAt) {
        this.customerId = customerId;
        this.variantId = variantId;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public int getVariantId() { return variantId; }
    public void setVariantId(int variantId) { this.variantId = variantId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
