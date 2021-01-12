package com.meli.quasar.dtos;

import lombok.Data;

import java.util.List;

@Data
public class TopSecretRequest {

    List<SatelliteDTO> satelliteDTOS;
}
