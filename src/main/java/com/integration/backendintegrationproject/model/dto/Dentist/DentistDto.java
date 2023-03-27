package com.integration.backendintegrationproject.model.dto.Dentist;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;

public record DentistDto(
    @JsonProperty ("license")
    Long license,
    @JsonProperty ("name")
    String name,
    @JsonProperty ("lastName")
    String lastName
){

}
