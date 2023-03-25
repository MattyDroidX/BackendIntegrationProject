package com.integration.backendintegrationproject.model.entities;

import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "address_id" )
    private Long addressId;
    private String street;
    private Integer number;
    private String zipCode;

    public Address(Long addressId, String street, Integer number, String zipCode) {
        this.addressId = addressId;
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
    }

    public Address(){}

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
