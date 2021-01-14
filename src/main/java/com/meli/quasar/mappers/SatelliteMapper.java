package com.meli.quasar.mappers;

import com.meli.quasar.dtos.SatelliteDTO;
import com.meli.quasar.entities.Satellite;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SatelliteMapper {

    public Satellite toEntity(SatelliteDTO dto) {

        if (dto == null) {
            throw new NullPointerException();
        }
        Satellite entity = new Satellite();
        entity.setName(dto.getName());
        entity.setDistance(dto.getDistance());
        if (dto.getMessage() != null && dto.getMessage().length > 0) {
            entity.setMessage(dto.getMessage());
        }
        return entity;
    }

    public SatelliteDTO toDto(Satellite entity) {

        if (entity == null) {
            throw new NullPointerException();
        }
        SatelliteDTO dto = new SatelliteDTO();
        dto.setName(entity.getName());
        dto.setDistance(entity.getDistance());
        if (entity.getMessage() != null && entity.getMessage().length > 0) {
            dto.setMessage(entity.getMessage());
        }
        return dto;
    }


    public List<Satellite> toEntityList(List<SatelliteDTO> dtos) {
        return dtos
                .parallelStream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

   public List<SatelliteDTO> toDtoList(List<Satellite> entities) {
        return entities
                .parallelStream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
