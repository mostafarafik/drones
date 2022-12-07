package com.redcode.drones.utils.mapper;

import com.redcode.drones.entities.Drone;
import com.redcode.drones.entities.Medication;
import com.redcode.drones.model.DroneDto;
import com.redcode.drones.model.MedicationDto;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface MedicationMapper {

    Medication toEntity(MedicationDto medicationDto);

    MedicationDto toDto(Medication medication);

    Set<MedicationDto> toDtoList(Set<Medication> medications);

    Set<Medication> map(Set<Medication> medications);
}
