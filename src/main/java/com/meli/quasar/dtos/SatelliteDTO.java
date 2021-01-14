package com.meli.quasar.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SatelliteDTO {

    private String name;
    private Double distance;
    private String[] message;
}
