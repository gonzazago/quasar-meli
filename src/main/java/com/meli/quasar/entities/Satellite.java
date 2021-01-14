package com.meli.quasar.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Satellite {

    private double distance;
    private String name;
    private String[] message;
    private Position position;

}
