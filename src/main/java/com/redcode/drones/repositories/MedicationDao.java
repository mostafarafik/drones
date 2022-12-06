package com.redcode.drones.repositories;

import com.redcode.drones.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationDao extends JpaRepository<Medication,Integer> {
}
