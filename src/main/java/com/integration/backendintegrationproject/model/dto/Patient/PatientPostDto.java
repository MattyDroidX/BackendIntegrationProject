package com.integration.backendintegrationproject.model.dto.Patient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.integration.backendintegrationproject.model.dto.AddressDto;
import com.integration.backendintegrationproject.model.entities.Address;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record PatientPostDto (
        @JsonProperty
        String name,
        @JsonProperty
        String surname,
        @JsonProperty
        Address address,
        @JsonProperty
        String DNI,
        @JsonProperty
        LocalDateTime appointmentDate){}