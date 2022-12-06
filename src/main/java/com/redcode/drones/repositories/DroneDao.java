package com.redcode.drones.repositories;

import com.redcode.drones.entities.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneDao extends JpaRepository<Drone,Integer> {
}
