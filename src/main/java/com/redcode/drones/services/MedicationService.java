package com.redcode.drones.services;

import com.redcode.drones.entities.Medication;
import com.redcode.drones.model.MedicationDto;

import java.util.List;
import java.util.Set;

public interface MedicationService {

    MedicationDto addNewMedication(MedicationDto medicationDto) throws Exception;

    MedicationDto updateExistMedicationById(MedicationDto updatedMedicationDto, Integer id) throws Exception;

    void deleteExistMedicationById(Integer id) throws Exception;

    MedicationDto getExistMedicationByCode(String code) throws Exception;

    List<MedicationDto> getAllMedications() throws Exception;

    Medication save(Medication medication) ;

}
