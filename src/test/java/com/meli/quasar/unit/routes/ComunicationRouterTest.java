package com.meli.quasar.unit.routes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meli.quasar.config.routers.ComunicationRouterConfig;
import com.meli.quasar.dtos.SatelliteDTO;
import com.meli.quasar.dtos.TopSecretResponseDTO;
import com.meli.quasar.entities.Position;
import com.meli.quasar.handler.ComunicationHandler;
import com.meli.quasar.services.ComunicationService;
import com.meli.quasar.services.impl.CommunicationServiceImple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static com.meli.quasar.constants.StringContstants.TOP_SECRET_PATH;
import static com.meli.quasar.unit.TestUtils.getSatellites;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ComunicationHandler.class, ComunicationRouterConfig.class})
@WebFluxTest
public class ComunicationRouterTest {

    @MockBean
    private ComunicationService service;

    @Autowired
    private ApplicationContext context;

    private WebTestClient client;


    @BeforeEach
    public void init() {
        client = WebTestClient.bindToApplicationContext(context).build();
    }


    @Test
    public void sendRequestMessage() throws JsonProcessingException {
        List<SatelliteDTO> satelliteDTOList = getSatellites();
        TopSecretResponseDTO responseDTO = TopSecretResponseDTO
                .builder()
                .message("algun Mensaje Oculto secreto")
                .position(new Position(-58.315, -69.551))
                .build();
        when(service.proccessMessage(satelliteDTOList)).thenReturn(responseDTO);

        client.post()
                .uri(TOP_SECRET_PATH)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(TopSecretResponseDTO.class);
    }

}
