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
import java.util.List;
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
        drone.setBatteryCapacity(100);
        droneDto.setBatteryCapacity(100);
        droneDto.setState(State.IDLE);
        return droneMapper.toDto(droneDao.save(drone));
    }

    @Override
    public DroneDto loadingDroneWithMedication(List<String> medicationCodes, String serialNumber) throws Exception {
        Drone drone = droneDao.findDroneBySerialNumber(serialNumber);
        if (drone != null && drone.getState().equals(State.IDLE)) {
            List<Medication> medications = medicationDao.findMedicationsByCodeIn(medicationCodes);
            if (medications != null && medications.size() > 0) {
                drone.setMedications(medications);
                drone.setState(State.LOADING);
                drone.setBatteryCapacity(75);
                return droneMapper.toDto(droneDao.save(drone));
            } else
                throw new Exception(String.format("Cannot find the medication with this codes to drone with serial Number [%s] ", serialNumber));
        }
        throw new Exception(String.format("Cannot find the drone with this serial Number [%s] or this drone is not idle", serialNumber));
    }

    @Override
    public List<MedicationDto> loadedMedication(String serialNumber) throws Exception {
        Drone drone = droneDao.findDroneBySerialNumber(serialNumber);
        if (drone != null) {
            return medicationMapper.toDtoList(drone.getMedications());
        }
        throw new Exception(String.format("Cannot find the drone with this serial Number [%s]", serialNumber));
    }

    @Override
    public List<DroneDto> getAvailableDrones() {
        List<Drone> drones = droneDao.findAllByState(State.IDLE);
        return droneMapper.toDtoList(drones);
    }

    @Override
    public Integer getBatteryLevel(String serialNumber) throws Exception {
        Drone drone = droneDao.findDroneBySerialNumber(serialNumber);
        if (drone != null) {
            return drone.getBatteryCapacity();
        }
        throw new Exception(String.format("Cannot find the drone with this serial Number [%s] ", serialNumber));
    }
}
