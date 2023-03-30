package com.integration.backendintegrationproject.model.entities;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "Appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "dentist_id")
    private Dentist dentistAppointment;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patientAppointment;

    private LocalDateTime registrationDate;

    public Appointment(Long id, Dentist dentistAppointment, Patient patientAppointment, LocalDateTime registrationDate) {
        this.id = id;
        this.dentistAppointment = dentistAppointment;
        this.patientAppointment = patientAppointment;
        this.registrationDate = registrationDate;
    }

    public Appointment(Dentist dentistAppointment, Patient patientAppointment, LocalDateTime registrationDate) {
        this.dentistAppointment = dentistAppointment;
        this.patientAppointment = patientAppointment;
        this.registrationDate = registrationDate;
    }


    public Appointment() {
    }

    public Appointment(Long dentistId, Long patientId, LocalDateTime registrationDate) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dentist getDentistAppointment() {
        return dentistAppointment;
    }

    public void setDentistAppointment(Dentist dentistAppointment) {
        this.dentistAppointment = dentistAppointment;
    }

    public Patient getPatientAppointment() {
        return patientAppointment;
    }

    public void setPatientAppointment(Patient patientAppointment) {
        this.patientAppointment = patientAppointment;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", dentistAppointment=" + dentistAppointment +
                ", patientAppointment=" + patientAppointment +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
