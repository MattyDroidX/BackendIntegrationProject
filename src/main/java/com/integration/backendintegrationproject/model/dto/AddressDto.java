package com.integration.backendintegrationproject.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record AddressDto(
        @JsonProperty
        String street,
        @JsonProperty
        Integer number,
        @JsonProperty
        String zipCode) {
}
