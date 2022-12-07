package com.redcode.drones.utils.mapper;

import com.redcode.drones.entities.Medication;
import com.redcode.drones.model.MedicationDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicationMapper {

    Medication toEntity(MedicationDto medicationDto);

    MedicationDto toDto(Medication medication);

    List<MedicationDto> toDtoList(List<Medication> medications);

    List<Medication> map(List<Medication> medications);
}
