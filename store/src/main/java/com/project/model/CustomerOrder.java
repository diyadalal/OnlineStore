package com.project.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.project.enums.OrderStatus;

public class CustomerOrder {
    private int orderId;
    private int customerId; // Foreign Key
    private int variantId;  // Foreign Key
    private int quantity;
    private LocalDateTime orderDate;

    public CustomerOrder() {}

    public CustomerOrder(int orderId, int customerId, int variantId, int quantity, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.variantId = variantId;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public int getVariantId() { return variantId; }
    public void setVariantId(int variantId) { this.variantId = variantId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
}
