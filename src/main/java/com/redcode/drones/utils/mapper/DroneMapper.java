package com.redcode.drones.utils.mapper;

import com.redcode.drones.entities.Drone;
import com.redcode.drones.model.DroneDto;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring",uses = MedicationMapper.class)
public interface DroneMapper {

    Drone toEntity(DroneDto droneDto);

    DroneDto toDto(Drone drone);

    List<DroneDto> toDtoList(List<Drone> drones);

}
