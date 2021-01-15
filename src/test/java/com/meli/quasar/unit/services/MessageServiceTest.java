package com.meli.quasar.unit.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meli.quasar.dtos.SatelliteDTO;
import com.meli.quasar.services.MessageService;
import com.meli.quasar.services.impl.MessageServiceImpl;
import com.meli.quasar.unit.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Import(MessageServiceImpl.class)
public class MessageServiceTest {

    List<SatelliteDTO> satellites = null;
    List<SatelliteDTO> satellitesWithOutMessages = null;
    @Autowired
    private MessageService service;


    @BeforeEach
    public void init() throws JsonProcessingException {
        satellites = TestUtils.getSatellites();
        satellitesWithOutMessages = TestUtils.getSatellitesWithOutMessageOrPosition();
    }


    @Test
     void testWithAllSatelliteWithMessage() {
        String message = service.getMessage(satellites);
        String messageExpected = "algun Mensaje Oculto secreto";
        assertAll(
                () -> assertNotNull(message),
                () -> assertEquals(messageExpected, message)
        );
    }


    @Test
     void testWithSatelliteWithOutMessage() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.getMessage(satellitesWithOutMessages);
        });
    }
}
