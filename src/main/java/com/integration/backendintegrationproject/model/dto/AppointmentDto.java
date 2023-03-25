package com.integration.backendintegrationproject.model.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public record AppointmentDto( @JsonProperty("dentist_id") Long dentist_id,
                              @JsonProperty ("patient_id") Long patient_id,
                              @JsonProperty ("lastName") LocalDateTime registrationDate){

}
