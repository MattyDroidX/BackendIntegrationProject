package com.integration.backendintegrationproject.model.dto.Dentist;
import com.fasterxml.jackson.annotation.JsonProperty;

public record DentistDto(


    @JsonProperty ("license")
    Long license,
    @JsonProperty ("name")
    String name,
    @JsonProperty ("lastName")
    String lastName
){

}
