package com.redcode.drones.repositories;

import com.redcode.drones.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MedicationDao extends JpaRepository<Medication, Integer> {
    Set<Medication> findMedicationsByCodeIn(Set<String> codes);
    Medication findMedicationByCode(String code);
}
