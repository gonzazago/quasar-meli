package com.meli.quasar.enums;


import com.meli.quasar.entities.Position;

import static com.meli.quasar.constants.StringContstants.*;

public enum SatelliteEnum {


    KENOBI(SATELLITE_KENOBI),
    SKYWALKER(SATELLITE_SKYWALKER),
    SATO(SATELLITE_SATO);

    private String name;


    SatelliteEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
