package com.meli.quasar.handler;

import com.meli.quasar.dtos.TopSecretRequestDTO;
import com.meli.quasar.dtos.TopSecretResponseDTO;
import com.meli.quasar.services.ComunicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

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
