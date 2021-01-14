package com.meli.quasar.dtos;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SatelliteDTO {

    private String name;
    private Double distance;
    private String[] message;
}
