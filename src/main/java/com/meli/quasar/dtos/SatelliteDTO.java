package com.meli.quasar.dtos;


import lombok.Data;

@Data
public class SatelliteDTO {

    private String name;
    private Double distance;
    private String[] message;
}
