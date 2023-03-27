package com.integration.backendintegrationproject.model.dto.Dentist;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record DentistUpdateDto(
        @JsonProperty ("license")
        Long license,
        @JsonProperty ("name")
        String name,
        @JsonProperty ("lastName") String lastName
){

}
