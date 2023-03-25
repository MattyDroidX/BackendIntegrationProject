package com.integration.backendintegrationproject.model.dto.Dentist;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public record DentistUpdateDto(
        @NotBlank( message = " license cannot be empty or null" )
        @JsonProperty ("license")
        Long license,
        @NotBlank( message = " name cannot be empty or null" )
        @JsonProperty ("name")
        String name,
        @NotBlank( message = " lastname cannot be empty or null" )
        @JsonProperty ("lastName") String lastName
){

}
