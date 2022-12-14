package com.redcode.drones.controllers;

import com.redcode.drones.model.DroneDto;
import com.redcode.drones.model.MedicationDto;
import com.redcode.drones.services.DroneService;
import com.redcode.drones.utils.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/drone/api/v1")
@RequiredArgsConstructor
@Api(tags = "Drone Api")
public class DroneController {
    private final DroneService droneService;

    @PostMapping(value = "/registerDrone", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Drone - register Drone")
    public ResponseEntity<?> registerDrone(@ApiParam(value = "Drone Model") @RequestBody DroneDto droneDto) {
        DroneDto dto;
        try {
            dto = droneService.registerDrone(droneDto);
        } catch (Exception ex) {
            return RestResponse.error(ex.getMessage());
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/loadingDroneWithMedication", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Drone - loading Drone With Medication")
    public ResponseEntity<?> loadingDroneWithMedication(
            @ApiParam(value = "medication Codes") @RequestBody List<String> medicationCodes,
            @ApiParam(value = "Drone serial number") @RequestParam String serialNumber) {
        DroneDto dto;
        try {
            dto = droneService.loadingDroneWithMedication(medicationCodes, serialNumber);
        } catch (Exception ex) {
            return RestResponse.error(ex.getMessage());
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/loadedMedication")
    @ApiOperation(value = "Drone - loaded Medication")
    public ResponseEntity<?> loadedMedication(@ApiParam(value = "Drone serial number") @RequestParam(name = "serialNumber") String serialNumber) {
        List<MedicationDto> medicationDtos;
        try {
            medicationDtos = droneService.loadedMedication(serialNumber);
        } catch (Exception ex) {
            return RestResponse.error(ex.getMessage());
        }
        return ResponseEntity.ok(medicationDtos);
    }

    @GetMapping(value = "/availableDrones")
    @ApiOperation(value = "Drone - available Drones")
    public ResponseEntity<?> getAvailableDrones() {
        List<DroneDto> droneDtos;
        try {
            droneDtos = droneService.getAvailableDrones();
        } catch (Exception ex) {
            return RestResponse.error(ex.getMessage());
        }
        return ResponseEntity.ok(droneDtos);
    }

    @GetMapping(value = "/batteryLevel")
    @ApiOperation(value = "Drone - battery Level")
    public ResponseEntity<?> getBatteryLevel(@ApiParam(value = "Drone serial number") @RequestParam(name = "serialNumber") String serialNumber) {
        Integer batteryLevel;
        try {
            batteryLevel = droneService.getBatteryLevel(serialNumber);
        } catch (Exception ex) {
            return RestResponse.error(ex.getMessage());
        }
        return ResponseEntity.ok(batteryLevel);
    }


}
