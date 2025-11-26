package com.project.model;

import java.math.BigDecimal;

public class Customers {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String address; // Combined address string
    private BigDecimal balance;
    // IMPORTANT: This holds the hashed BCrypt string, NEVER plain text.
    private String passwordHash;

    // Empty Constructor (often needed by frameworks)
    public Customers() {}

    // Full Constructor (useful for reading from DB)
    public Customers(int customerId, String firstName, String lastName, String email, String address, BigDecimal balance, String passwordHash) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.balance = balance;
        this.passwordHash = passwordHash;
    }

    // Getters and Setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}
