package com.project.model;

import java.time.LocalDateTime;

import com.project.enums.InventoryReason;

public class InventoryLog {
    private int logId;
    private int variantId;
    private int changeQty;
    private InventoryReason reason;
    private LocalDateTime createdAt;

    public InventoryLog() {}

    public InventoryLog(int logId, int variantId, int changeQty, InventoryReason reason, LocalDateTime createdAt) {
        this.logId = logId;
        this.variantId = variantId;
        this.changeQty = changeQty;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getLogId() { return logId; }
    public void setLogId(int logId) { this.logId = logId; }
    public int getVariantId() { return variantId; }
    public void setVariantId(int variantId) { this.variantId = variantId; }
    public int getChangeQty() { return changeQty; }
    public void setChangeQty(int changeQty) { this.changeQty = changeQty; }
    public InventoryReason getReason() { return reason; }
    public void setReason(InventoryReason reason) { this.reason = reason; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
