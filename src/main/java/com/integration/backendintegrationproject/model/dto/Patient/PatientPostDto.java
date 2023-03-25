package com.integration.backendintegrationproject.model.dto.Patient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.integration.backendintegrationproject.model.entities.Address;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record PatientPostDto (
        @NotBlank( message = "Name cannot be empty or null" )
        @JsonProperty
        String name,
        @NotBlank( message = "Surname cannot be empty or null" )
        @JsonProperty
        String surname,
        @JsonProperty
        Address address,
        @JsonProperty
        LocalDateTime registrationDate){}