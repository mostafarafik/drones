package com.redcode.drones.services.imp;

import com.redcode.drones.entities.Medication;
import com.redcode.drones.model.MedicationDto;
import com.redcode.drones.repositories.MedicationDao;
import com.redcode.drones.services.MedicationService;
import com.redcode.drones.utils.mapper.MedicationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MedicationServiceImp implements MedicationService {

    final MedicationDao medicationDao;
    final MedicationMapper medicationMapper;

    @Override
    public MedicationDto addNewMedication(MedicationDto medicationDto) {

        Medication medication = medicationMapper.toEntity(medicationDto);
        return medicationMapper.toDto(medicationDao.save(medication));
    }

    @Override
    public MedicationDto updateExistMedicationByCode(MedicationDto updatedMedicationDto, Integer id) {

        Medication medication = medicationDao.findById(id).get();
        if (medication != null) {
            Medication updatedMedication = medicationMapper.toEntity(updatedMedicationDto);
            medication = updateMedication(medication, updatedMedication);
            return medicationMapper.toDto(medicationDao.save(medication));
        }
        return null;
    }

    private Medication updateMedication(Medication medication, Medication updatedMedication) {
        medication.setCode(updatedMedication.getCode());
        medication.setName(updatedMedication.getName());
        medication.setWeight(updatedMedication.getWeight());
        if (updatedMedication.getImage() != null)
            medication.setImage(updatedMedication.getImage());
        return medication;
    }

    @Override
    public void deleteExistMedicationByCode(Integer id) {
        Medication medication = medicationDao.findById(id).get();
        if (medication != null)
            medicationDao.delete(medication);
    }

    @Override
    public MedicationDto getExistMedicationByCode(String code) {
        Medication medication = medicationDao.findMedicationByCode(code);
        return medicationMapper.toDto(medication);
    }

    @Override
    public List<MedicationDto> getAllMedications() {
        return medicationMapper.toDtoList(medicationDao.findAll());
    }
}
