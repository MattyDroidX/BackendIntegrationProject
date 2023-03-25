package com.integration.backendintegrationproject.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "dentist_id")
    private Dentist dentistAppointment;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "patient_id")
    private Patient patientAppointment;

    private LocalDateTime registrationDate;
}
