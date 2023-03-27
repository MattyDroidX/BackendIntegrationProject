package com.integration.backendintegrationproject.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity

public class Patient {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "patient_id" )
    private Long id;
    private String name;
    private String surname;
    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "address_id" )
    private Address address;
    private String DNI;
    private LocalDateTime appointmentDate;

    public Patient(String name, String surname, Address address, String DNI, LocalDateTime appointmentDate) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.DNI = DNI;
        this.appointmentDate = appointmentDate;
    }

    public Patient(Long id, String name, String surname, Address address, String DNI, LocalDateTime appointmentDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.DNI = DNI;
        this.appointmentDate = appointmentDate;
    }

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address=" + address +
                ", DNI='" + DNI + '\'' +
                ", appointmentDate=" + appointmentDate +
                '}';
    }
}


