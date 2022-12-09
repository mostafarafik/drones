package com.redcode.drones.controllers;

import com.redcode.drones.entities.DroneAudit;
import com.redcode.drones.model.DroneDto;
import com.redcode.drones.model.MedicationDto;
import com.redcode.drones.services.DroneAuditService;
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
@RequestMapping(value = "/droneAudit/api/v1")
@RequiredArgsConstructor
@Api(tags = "Drone Audit Api")
public class DroneAuditController {
    private final DroneAuditService droneAuditService;

    @GetMapping(value = "/getDroneAudit")
    @ApiOperation(value = "Drone Audit - get Drone Audit")
    public ResponseEntity<?> getDroneAudit(@ApiParam(value = "Drone serial number") @RequestParam(name = "serialNumber") String serialNumber) {
        List<DroneAudit> droneAudits;
        try {
            droneAudits = droneAuditService.getAllActionForDrone(serialNumber);
        } catch (Exception ex) {
            return RestResponse.error(ex.getMessage());
        }
        return ResponseEntity.ok(droneAudits);
    }



}
