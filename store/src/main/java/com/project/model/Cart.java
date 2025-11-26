package com.project.model;

public class Cart {
    private int cartId;
    private int customerId; // Foreign Key
    private int variantId;  // Foreign Key
    private int quantity;

    public Cart() {}

    public Cart(int cartId, int customerId, int variantId, int quantity) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.variantId = variantId;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public int getVariantId() { return variantId; }
    public void setVariantId(int variantId) { this.variantId = variantId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
