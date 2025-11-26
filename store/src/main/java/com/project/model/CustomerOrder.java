package com.project.model;

import java.time.LocalDateTime;

import com.project.enums.OrderStatus;
import com.project.enums.PaymentMethod;
import com.project.enums.PaymentStatus;

public class CustomerOrder {
    private int orderId;
    private int customerId;
    private int addressId;
    private Double totalPrice;
    private OrderStatus orderStatus;
    // Payment Info
    private Double paymentTotal;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentDate;
    private PaymentMethod paymentMethod;
    private LocalDateTime createdAt;

    public CustomerOrder() {}

    public CustomerOrder(int orderId, int customerId, int addressId, Double totalPrice, OrderStatus orderStatus,
                         Double paymentTotal, PaymentStatus paymentStatus, LocalDateTime paymentDate,
                         PaymentMethod paymentMethod, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.addressId = addressId;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.paymentTotal = paymentTotal;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public int getAddressId() { return addressId; }
    public void setAddressId(int addressId) { this.addressId = addressId; }
    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
    public OrderStatus getOrderStatus() { return orderStatus; }
    public void setOrderStatus(OrderStatus orderStatus) { this.orderStatus = orderStatus; }
    public Double getPaymentTotal() { return paymentTotal; }
    public void setPaymentTotal(Double paymentTotal) { this.paymentTotal = paymentTotal; }
    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus paymentStatus) { this.paymentStatus = paymentStatus; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
