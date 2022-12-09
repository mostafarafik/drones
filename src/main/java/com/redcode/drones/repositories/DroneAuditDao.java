package com.redcode.drones.repositories;

import com.redcode.drones.entities.DroneAudit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneAuditDao extends JpaRepository<DroneAudit,Integer> {
    List<DroneAudit> findAllBySerialNumberOrderByActionDate(String serialNumber);
}
