package com.project.model;

public class OrderItem {
    private int itemId;
    private int orderId;
    private int variantId;
    private int quantity;

    public OrderItem() {}

    public OrderItem(int itemId, int orderId, int variantId, int quantity) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.variantId = variantId;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getVariantId() { return variantId; }
    public void setVariantId(int variantId) { this.variantId = variantId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

}
