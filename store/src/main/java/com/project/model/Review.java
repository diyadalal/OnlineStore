package com.project.model;

import java.time.LocalDateTime;

public class Review {
    private int reviewId;
    private int customerId;
    private int variantId;
    private int rating;
    private String description;
    private LocalDateTime createdAt;

    public Review() {}

    public Review(int reviewId, int customerId, int variantId, int rating, String description, LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.customerId = customerId;
        this.variantId = variantId;
        this.rating = rating;
        this.description = description;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getReviewId() { return reviewId; }
    public void setReviewId(int reviewId) { this.reviewId = reviewId; }
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public int getVariantId() { return variantId; }
    public void setVariantId(int variantId) { this.variantId = variantId; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
