package com.redcode.drones.services.imp;

import com.redcode.drones.entities.DroneAudit;
import com.redcode.drones.entities.Medication;
import com.redcode.drones.model.MedicationDto;
import com.redcode.drones.repositories.DroneAuditDao;
import com.redcode.drones.repositories.MedicationDao;
import com.redcode.drones.services.DroneAuditService;
import com.redcode.drones.services.MedicationService;
import com.redcode.drones.utils.mapper.MedicationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DroneAuditServiceImp implements DroneAuditService {

    final DroneAuditDao droneAuditDao;

    @Override
    public DroneAudit saveDroneAction(String serialNumber, String action) {
        DroneAudit droneAudit = new DroneAudit();
        droneAudit.setAction(action);
        droneAudit.setSerialNumber(serialNumber);
        droneAudit.setActionDate(new Date());
        return droneAuditDao.save(droneAudit);
    }

    @Override
    public List<DroneAudit> getAllActionForDrone(String serialNumber) {
        return droneAuditDao.findAllBySerialNumberOrderByActionDate(serialNumber);
    }
}
