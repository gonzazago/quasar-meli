package com.meli.quasar.handler;

import com.meli.quasar.config.SatelliteConfig;
import com.meli.quasar.dtos.SatelliteDTO;
import com.meli.quasar.dtos.TopSecretRequestDTO;
import com.meli.quasar.dtos.TopSecretResponseDTO;
import com.meli.quasar.entities.Position;
import com.meli.quasar.entities.Satellite;
import com.meli.quasar.services.ComunicationService;
import com.meli.quasar.services.LocationService;
import com.meli.quasar.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

import static com.meli.quasar.constants.StringContstants.SATELLITE_KENOBY;

@Component
@Slf4j
public class ComunicationHandler {


    @Autowired
    private ComunicationService comunicationService;


    public Mono<ServerResponse> getLocation(ServerRequest request) {
        Mono<TopSecretRequestDTO> requestDTO = request.bodyToMono(TopSecretRequestDTO.class);


        return requestDTO
                .flatMap(tSecret -> {
                    if (tSecret.getSatelliteDTOS().size() < 2) {
                        log.error("Need at least two satellite but  passed: {} satellites. ", tSecret.getSatelliteDTOS().size());
                        return ServerResponse.notFound().build();
                    }
                    try {
                        TopSecretResponseDTO response = comunicationService.proccessMessage(tSecret.getSatelliteDTOS());
                        return ServerResponse.ok()
                                .body(
                                        BodyInserters.fromValue(response))
                                .switchIfEmpty(ServerResponse.notFound().build());
                    } catch (Exception e) {
                        return ServerResponse.
                                status(HttpStatus.NOT_FOUND)
                                .build();
                    }

                });


    }
}
