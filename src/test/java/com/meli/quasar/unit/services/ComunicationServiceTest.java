package com.meli.quasar.unit.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meli.quasar.dtos.SatelliteDTO;
import com.meli.quasar.dtos.TopSecretResponseDTO;
import com.meli.quasar.entities.Position;
import com.meli.quasar.exceptions.ComunicationException;
import com.meli.quasar.exceptions.LocationException;
import com.meli.quasar.mappers.SatelliteMapper;
import com.meli.quasar.services.ComunicationService;
import com.meli.quasar.services.LocationService;
import com.meli.quasar.services.MessageService;
import com.meli.quasar.services.impl.CommunicationServiceImple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.meli.quasar.unit.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@Import(CommunicationServiceImple.class)
public class ComunicationServiceTest {

    @Autowired
    private ComunicationService service;


    @MockBean
    private LocationService locationService;

    @MockBean
    private MessageService messageService;


    @MockBean
    SatelliteMapper mapper;

    List<SatelliteDTO> satellitesMock = null;
    List<SatelliteDTO> satellitesWithOutPositions = null;


    @BeforeEach
    public void init() throws JsonProcessingException {
        satellitesMock = getSatellites();
        satellitesWithOutPositions = getSatellitesWithOutMessageOrPosition();
    }

    @Test
    void proccessMessageTest() {
        String messageExpected = "algun Mensaje Oculto secreto";
        Position positionExpected = Position.builder()
                .positionX(-59.42262429243444)
                .positionY(-69.9519130926232).build();
        when(locationService.getLocation(getDistances(satellitesMock))).thenReturn(positionExpected);
        when(messageService.getMessage(satellitesMock)).thenReturn(messageExpected);
        TopSecretResponseDTO response = service.proccessMessage(satellitesMock);

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(messageExpected, response.getMessage()),
                () -> assertEquals(positionExpected, response.getPosition())
        );
    }


    @Test
    void processMessageTest_withInvalidMessage() {
        String messageExpected = "algun Mensaje Oculto secreto";

        when(locationService.getLocation(getDistances(satellitesWithOutPositions))).thenThrow(LocationException.class);
        when(messageService.getMessage(satellitesWithOutPositions)).thenThrow(IllegalArgumentException.class);


        assertThrows(ComunicationException.class, () -> {
            service.proccessMessage(satellitesWithOutPositions);
        });

    }


}
