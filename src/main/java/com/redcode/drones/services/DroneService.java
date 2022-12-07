package com.redcode.drones.services;

import com.redcode.drones.model.DroneDto;
import com.redcode.drones.model.MedicationDto;

import java.util.List;
import java.util.Set;

public interface DroneService {

    DroneDto registerDrone(DroneDto droneDto) throws Exception;

    DroneDto loadingDroneWithMedication(List<String> medicationCodes, String serialNumber) throws Exception;

    List<MedicationDto> loadedMedication(String serialNumber) throws Exception;

    List<DroneDto> getAvailableDrones() throws Exception;

    Integer getBatteryLevel(String serialNumber) throws Exception;

}
