package com.meli.quasar.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TopSecretRequestDTO {

    @JsonProperty("satellites")
    List<SatelliteDTO> satelliteDTOS;
}
