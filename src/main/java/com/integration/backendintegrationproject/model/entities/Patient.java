package com.integration.backendintegrationproject.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
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
}


