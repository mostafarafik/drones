package com.redcode.drones.services;

import com.redcode.drones.entities.DroneAudit;
import java.util.List;

public interface DroneAuditService {

    DroneAudit saveDroneAction(String serialNumber,String action);

    List<DroneAudit> getAllActionForDrone(String serialNumber);

}
