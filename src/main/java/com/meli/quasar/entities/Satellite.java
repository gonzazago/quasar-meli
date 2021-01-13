package com.meli.quasar.entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Satellite {

    private double distance;
    private String name;
    private String[] messages;
    private Position position;

}
