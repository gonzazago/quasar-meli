package com.meli.quasar.enums;

import com.meli.quasar.constants.StringContstants;
import com.meli.quasar.entities.Position;

public enum SatelliteEnum {

    KENOBI(StringContstants.SATELLITE_KENOBI, new Position(-500.0, -200.0)),
    SKYWALKER(StringContstants.SATELLITE_SKYWALKER, new Position(100.0, -100.0)),
    SATO(StringContstants.SATELLITE_SATO, new Position(500.0, 100.0));

    private String name;
    private Position position;
    private Integer order;

    SatelliteEnum(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }
}
