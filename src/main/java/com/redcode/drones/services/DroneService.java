package com.redcode.drones.services;

import com.redcode.drones.model.DroneDto;
import com.redcode.drones.model.MedicationDto;

import java.util.Set;

public interface DroneService {

    DroneDto registerDrone(DroneDto droneDto);
    DroneDto loadingDroneWithMedication(Set<String> medicationCodes,String serialNumber);
    Set<MedicationDto> loadedMedication(String serialNumber);
    Set<DroneDto> getAvailableDrones();
    Integer getBatteryLevel(String serialNumber);

}
