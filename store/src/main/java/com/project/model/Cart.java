package com.project.model;

public class Cart {
    private int cartId;
    private int customerId;
    private int variantId;

    public Cart() {}

    public Cart(int cartId, int customerId, int variantId) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.variantId = variantId;
    }

    public int getCartId() { return cartId; }
    public void setCartId(int cartId) { this.cartId = cartId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getVariantId() { return variantId; }
    public void setVariantId(int variantId) { this.variantId = variantId; }
}
