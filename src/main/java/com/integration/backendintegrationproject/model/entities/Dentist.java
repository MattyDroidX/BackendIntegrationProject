package com.integration.backendintegrationproject.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dentist")
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dentist_id")
    private Long id;
    private Long license;
    private String name;
    private String lastName;

    public Dentist(Long id, Long license, String name, String lastName) {
        this.id = id;
        this.license = license;
        this.name = name;
        this.lastName = lastName;
    }

    public Dentist(Long license, String name, String lastName) {
        this.license = license;
        this.name = name;
        this.lastName = lastName;
    }

    public Dentist() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLicense() {
        return license;
    }

    public void setLicense(Long license) {
        this.license = license;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Dentist{" +
                "id=" + id +
                ", license=" + license +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
