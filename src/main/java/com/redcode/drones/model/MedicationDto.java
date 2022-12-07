package com.redcode.drones.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class MedicationDto {

    @JsonProperty("medicationId")
    private Integer medicationId;

    @JsonProperty("name")
    @NotNull(message = "name Cannot be null")
    @Pattern(regexp = "^[A-Za-z0-9_-]*$", message = "name is Incorrect")
    private String name;

    @JsonProperty("weight")
    @NotNull(message = "weight Cannot be null")
    private Double weight;

    @JsonProperty("code")
    @NotNull(message = "code Cannot be null")
    @Pattern(regexp = "^[A-Z0-9_]*$", message = "State is Incorrect")
    private String code;

    @JsonProperty("image")
    private byte[] image;

}
