package com.integration.backendintegrationproject.model.dto.Appointment;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.integration.backendintegrationproject.model.entities.Dentist;
import com.integration.backendintegrationproject.model.entities.Patient;

import java.time.LocalDateTime;
public record AppointmentDto(
        @JsonProperty("dentist_id")
        Long dentist_id,

        @JsonProperty ("patient_id")
        Long patient_id,

        @JsonProperty ("appointment")
        LocalDateTime registrationDate){

}
