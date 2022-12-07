package com.redcode.drones.repositories;

import com.redcode.drones.entities.Drone;
import com.redcode.drones.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface DroneDao extends JpaRepository<Drone,Integer> {
    Drone findDroneBySerialNumber(String serialNumber);
    Set<Drone> findAllByState(State state);
}
