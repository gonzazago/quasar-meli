package com.meli.quasar.repository;

import com.meli.quasar.entities.Position;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

import static com.meli.quasar.constants.StringContstants.*;

@Component
public class SatelliteRepository {

    @Value("${satellite.positions.kenoby-x}")
    private double SATELLITE_KENOBI_X;
    @Value("${satellite.positions.kenoby-y}")
    private double SATELLITE_KENOBI_Y;

    @Value("${satellite.positions.skywalker-x}")
    private double SATELLITE_SKYWALKER_X;
    @Value("${satellite.positions.skywalker-y}")
    private double SATELLITE_SKYWALKER_Y;

    @Value("${satellite.positions.sato-x}")
    private double SATELLITE_SATO_X;
    @Value("${satellite.positions.sato-y}")
    private double SATELLITE_SATO_Y;

    public Position findPositionByName(String name) {
        Position pos = null;

        switch (name) {
            case SATELLITE_KENOBI:
                pos = new Position(SATELLITE_KENOBI_X, SATELLITE_KENOBI_Y);
                break;
            case SATELLITE_SKYWALKER:
                pos = new Position(SATELLITE_SKYWALKER_X, SATELLITE_SKYWALKER_Y);
                break;
            case SATELLITE_SATO:
                pos = new Position(SATELLITE_SATO_X, SATELLITE_SATO_Y);
                break;
            default:
                throw new NoSuchElementException("Satellite with name is  not found");
        }

        return pos;

    }


}
