package com.redcode.drones.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redcode.drones.entities.Medication;
import com.redcode.drones.enums.Model;
import com.redcode.drones.enums.State;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class DroneDto {

    @JsonProperty("droneId")
    private Integer droneId;

    @JsonProperty("serialNumber")
    @NotNull(message = "Serial Number Cannot be null")
    @Size(max = 100, message = "Serial Number Max size is 100 characters")
    private String serialNumber;

    @JsonProperty("model")
    @NotNull(message = "model Cannot be null")
    @Pattern(regexp = "Lightweight|Middleweight|Cruiserweight|Heavyweight", message = "Model name is Incorrect")
    private Model model;

    @JsonProperty("weightLimit")
    @NotNull(message = "weightLimit Cannot be null")
    @Size(max = 500, message = "Weight Limit Max size is 500 gram")
    private Double weightLimit;

    @JsonProperty("batteryCapacity")
    private Integer batteryCapacity;

    @JsonProperty("state")
    @Pattern(regexp = "IDLE|LOADING|LOADED|DELIVERING|DELIVERED|RETURNING", message = "State is Incorrect")
    private State state;

    @JsonProperty("medications")
    private Set<Medication> medications;
}
