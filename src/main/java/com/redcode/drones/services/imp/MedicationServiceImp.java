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
import java.util.List;

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
    public MedicationDto updateExistMedicationById(MedicationDto updatedMedicationDto, Integer id) throws Exception {

        Medication medication = medicationDao.findById(id).get();
        if (medication != null) {
            Medication updatedMedication = medicationMapper.toEntity(updatedMedicationDto);
            medication = updateMedication(medication, updatedMedication);
            return medicationMapper.toDto(medicationDao.save(medication));
        }
        throw new Exception(String.format("Cannot find the Medication with this Id [%s] ", id));
    }

    private Medication updateMedication(Medication medication, Medication updatedMedication) {
        medication.setCode(updatedMedication.getCode());
        medication.setName(updatedMedication.getName());
        medication.setWeight(updatedMedication.getWeight());
        if (updatedMedication.getImage() != null) medication.setImage(updatedMedication.getImage());
        return medication;
    }

    @Override
    public void deleteExistMedicationById(Integer id) throws Exception {
        Medication medication = medicationDao.findById(id).get();
        if (medication != null) medicationDao.delete(medication);
        else throw new Exception(String.format("Cannot find the Medication with this Id [%s] ", id));
    }

    @Override
    public MedicationDto getExistMedicationByCode(String code) throws Exception {
        Medication medication = medicationDao.findMedicationByCode(code);
        if (medication != null) return medicationMapper.toDto(medication);
        throw new Exception(String.format("Cannot find the Medication with this code [%s] ", code));
    }

    @Override
    public List<MedicationDto> getAllMedications() {
        return medicationMapper.toDtoList(medicationDao.findAll());
    }

    @Override
    public Medication save(Medication medication) {
        return medicationDao.save(medication);
    }
}
