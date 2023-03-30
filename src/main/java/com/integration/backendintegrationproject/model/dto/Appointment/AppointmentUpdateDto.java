package com.integration.backendintegrationproject.model.dto.Appointment;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.integration.backendintegrationproject.model.entities.Dentist;
import com.integration.backendintegrationproject.model.entities.Patient;

import java.time.LocalDateTime;
public record AppointmentUpdateDto(
        @JsonProperty("dentist")
        Dentist dentist,

        @JsonProperty ("patient")
        Patient patient,
        @JsonProperty ("appointment")
        LocalDateTime registrationDate){

}
