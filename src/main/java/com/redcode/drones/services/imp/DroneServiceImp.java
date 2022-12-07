package com.redcode.drones.services.imp;

import com.redcode.drones.entities.Drone;
import com.redcode.drones.entities.Medication;
import com.redcode.drones.enums.State;
import com.redcode.drones.model.DroneDto;
import com.redcode.drones.model.MedicationDto;
import com.redcode.drones.repositories.DroneDao;
import com.redcode.drones.repositories.MedicationDao;
import com.redcode.drones.services.DroneService;
import com.redcode.drones.utils.mapper.DroneMapper;
import com.redcode.drones.utils.mapper.MedicationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DroneServiceImp implements DroneService {

    final DroneDao droneDao;
    final DroneMapper droneMapper;
    final MedicationDao medicationDao;
    final MedicationMapper medicationMapper;


    @Override
    public DroneDto registerDrone(DroneDto droneDto) {
        Drone drone = droneMapper.toEntity(droneDto);
        drone.setState(State.IDLE);
        droneDto.setState(State.IDLE);
        return droneMapper.toDto(droneDao.save(drone));
    }

    @Override
    public DroneDto loadingDroneWithMedication(Set<String> medicationCodes, String serialNumber) {
        Drone drone = droneDao.findDroneBySerialNumber(serialNumber);
        if (drone != null && drone.getState().equals(State.IDLE)) {
            Set<Medication> medications = medicationDao.findMedicationsByCodeIn(medicationCodes);
            if (medications != null && medications.size() > 0) {
                drone.setMedications(medications);
                drone.setState(State.LOADING);
                return droneMapper.toDto(droneDao.save(drone));
            }
        }
        return null;
    }

    @Override
    public Set<MedicationDto> loadedMedication(String serialNumber) {
        Drone drone = droneDao.findDroneBySerialNumber(serialNumber);
        if (drone != null && drone.getMedications() != null && drone.getMedications().size() > 0) {
            return medicationMapper.toDtoList(drone.getMedications());
        }
        return null;
    }

    @Override
    public Set<DroneDto> getAvailableDrones() {
        Set<Drone> drones = droneDao.findAllByState(State.IDLE);
        return droneMapper.toDtoList(drones);
    }

    @Override
    public Integer getBatteryLevel(String serialNumber) {
        Drone drone = droneDao.findDroneBySerialNumber(serialNumber);
        if (drone != null) {
            return drone.getBatteryCapacity();
        }
        return null;
    }
}
