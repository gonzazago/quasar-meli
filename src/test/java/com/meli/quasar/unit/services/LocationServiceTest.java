package com.meli.quasar.unit.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.meli.quasar.dtos.SatelliteDTO;
import com.meli.quasar.entities.Position;
import com.meli.quasar.exceptions.LocationException;
import com.meli.quasar.services.LocationService;
import com.meli.quasar.services.MessageService;
import com.meli.quasar.services.impl.LocationServiceImple;
import com.meli.quasar.services.impl.MessageServiceImpl;
import com.meli.quasar.unit.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@Import(LocationServiceImple.class)
public class LocationServiceTest {


    List<SatelliteDTO> satellites = null;
    List<SatelliteDTO> satellitesWithOutPositions = null;
    @Autowired
    private LocationService service;


    @BeforeEach
    public void init() throws JsonProcessingException {
        satellites = TestUtils.getSatellites();
        satellitesWithOutPositions = TestUtils.getSatellitesWithOutMessageOrPosition();
    }


    @Test
    void testWithAllSatelliteWithMessage() {
        List<Double> distances = satellites
                .parallelStream()
                .map(SatelliteDTO::getDistance)
                .collect(Collectors.toList());
        Position position = service.getLocation(distances);
        Position expected = Position.builder()
                .positionX(-59.42262429243444)
                .positionY(-69.9519130926232).build();
        System.out.println(position.toString());
        assertAll(
                () -> assertNotNull(position),
                () -> assertEquals(expected, position)
        );
    }


    @Test
    void testWithSatelliteWithOutMessage() {
        List<Double> distances = satellitesWithOutPositions
                .parallelStream()
                .filter(s -> s.getDistance() != null)
                .map(SatelliteDTO::getDistance)
                .collect(Collectors.toList());

        assertThrows(LocationException.class, () -> {
            service.getLocation( distances);
        });

    }

}
