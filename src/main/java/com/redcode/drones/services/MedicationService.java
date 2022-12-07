package com.redcode.drones.services;

import com.redcode.drones.model.MedicationDto;

import java.util.Set;

public interface MedicationService {

    MedicationDto addNewMedication(MedicationDto medicationDto);

    MedicationDto updateExistMedicationByCode(MedicationDto updatedMedicationDto, Integer id);

    void deleteExistMedicationByCode(Integer id);

    MedicationDto getExistMedicationByCode(String code);

    Set<MedicationDto> getAllMedications();

}
