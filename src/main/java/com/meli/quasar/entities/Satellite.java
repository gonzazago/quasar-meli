package com.meli.quasar.entities;

import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class Satellite {

    private double distance;
    private String name;
    private String[] message;
    private Position position;

}
