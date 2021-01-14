package com.meli.quasar.mappers;

import com.meli.quasar.dtos.SatelliteDTO;
import com.meli.quasar.entities.Satellite;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SatelliteMapper {

    Satellite toEntity(SatelliteDTO dto);
    SatelliteDTO toDto(Satellite entity);
    List<Satellite> toEntityList(List<SatelliteDTO>dtos);
    List<SatelliteDTO> toDtoList(List<Satellite>entities);
}
