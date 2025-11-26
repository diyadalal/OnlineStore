package com.project.model;

public class Address {
    private int addressId;
    private Integer customerId; // Integer because SQL allows NULL (though unlikely for an address)
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private boolean isDefault;

    public Address() {}

    public Address(int addressId, Integer customerId, String street, String city, String state, String zipCode, boolean isDefault) {
        this.addressId = addressId;
        this.customerId = customerId;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.isDefault = isDefault;
    }

    // Getters and Setters
    public int getAddressId() { return addressId; }
    public void setAddressId(int addressId) { this.addressId = addressId; }
    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
    public boolean isDefault() { return isDefault; }
    public void setDefault(boolean isDefault) { this.isDefault = isDefault; }
}
