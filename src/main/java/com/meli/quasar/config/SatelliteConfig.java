package com.meli.quasar.config;

import com.meli.quasar.entities.Position;
import com.meli.quasar.entities.Satellite;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.meli.quasar.constants.StringContstants.*;

@Configuration
public class SatelliteConfig {
    @Value("${satellite.names.kenoby}")
    private String kenobyName;

    @Value("${satellite.positions.kenoby-x}")
    private String kenobyPositionX;

    @Value("${satellite.positions.kenoby-y}")
    private String kenobyPositionY;

    @Value("${satellite.names.skywalker}")
    private String skywalkerName;

    @Value("${satellite.positions.skywalker-x}")
    private String skywalkerPositionX;

    @Value("${satellite.positions.skywalker-y}")
    private String skywalkerPositionY;

    @Value("${satellite.names.sato}")
    private String satoName;


    @Value("${satellite.positions.sato-x}")
    private String satoPositionX;

    @Value("${satellite.positions.sato-y}")
    private String satoPositionY;


    @Bean
    @Qualifier(SATELLITE_KENOBY)
    public Satellite getKenobi() {
        return Satellite.builder()
                .name(kenobyName)
                .position(new Position(Double.parseDouble(kenobyPositionX), Double.parseDouble(kenobyPositionY)))
                .build();
    }


    @Bean
    @Qualifier(SATELLITE_SKYWALKER)
    public Satellite getSkywalker() {
        return Satellite.builder()
                .name(kenobyName)
                .position(new Position(Double.parseDouble(kenobyPositionX), Double.parseDouble(kenobyPositionY)))
                .build();
    }

    @Bean
    @Qualifier(SATELLITE_SATO)
    public Satellite getSato() {
        return Satellite.builder()
                .name(kenobyName)
                .position(new Position(Double.parseDouble(kenobyPositionX), Double.parseDouble(kenobyPositionY)))
                .build();
    }
}
