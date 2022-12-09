package com.redcode.drones.controllers;

import com.redcode.drones.model.MedicationDto;
import com.redcode.drones.services.MedicationService;
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
@RequestMapping(value = "/medication/api/v1")
@RequiredArgsConstructor
@Api(tags = "Medication Api")
public class MedicationController {

    private final MedicationService medicationService;

    @PostMapping(value = "/addNewMedication", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Medication - add New Medication")
    public ResponseEntity<?> addNewMedication(@ApiParam(value = "Medication Model") @RequestBody MedicationDto medicationDto) {
        MedicationDto dto;
        try {
            dto = medicationService.addNewMedication(medicationDto);
        } catch (Exception ex) {
            return RestResponse.error(ex.getMessage());
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/updateExistMedicationById/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Medication - update Exist Medication By Id")
    public ResponseEntity<?> updateExistMedicationById(
            @ApiParam(value = "Medication Model") @RequestBody MedicationDto medicationDto,
            @ApiParam(value = "Medication id") @PathVariable Integer id) {
        MedicationDto dto;
        try {
            dto = medicationService.updateExistMedicationById(medicationDto, id);
        } catch (Exception ex) {
            return RestResponse.error(ex.getMessage());
        }
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/deleteExistMedicationById/{id}")
    @ApiOperation(value = "Medication - delete Exist Medication By Id")
    public ResponseEntity<?> deleteExistMedicationById(@ApiParam(value = "Medication id") @PathVariable Integer id) {
        try {
            medicationService.deleteExistMedicationById(id);
        } catch (Exception ex) {
            return RestResponse.error(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getExistMedicationByCode")
    @ApiOperation(value = "Medication - get Exist Medication By Code")
    public ResponseEntity<?> getExistMedicationByCode(@ApiParam(value = "Medication code") @RequestParam(name = "code") String code) {
        MedicationDto dto;
        try {
            dto = medicationService.getExistMedicationByCode(code);
        } catch (Exception ex) {
            return RestResponse.error(ex.getMessage());
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/getAllMedications")
    @ApiOperation(value = "Medication - get All Medications")
    public ResponseEntity<?> getAllMedications() {
        List<MedicationDto> medicationDtos;
        try {
            medicationDtos = medicationService.getAllMedications();
        } catch (Exception ex) {
            return RestResponse.error(ex.getMessage());
        }
        return ResponseEntity.ok(medicationDtos);
    }


}
