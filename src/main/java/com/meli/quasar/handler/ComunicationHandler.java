package com.meli.quasar.handler;

import com.meli.quasar.dtos.SatelliteDTO;
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

import java.util.List;

import static com.meli.quasar.constants.StringContstants.PATH_VARIABLE_NAME;

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

    public Mono<ServerResponse> updatePosition(ServerRequest request) {

        Mono<SatelliteDTO> requestDTO = request.bodyToMono(SatelliteDTO.class);
        String name = request.pathVariable(PATH_VARIABLE_NAME);
        log.info("Update satellite: {}", name);
        return requestDTO.flatMap(
                satellite -> {
                    comunicationService.updateSatellite(name, satellite);
                    return ServerResponse.ok().build();
                }
        );
    }

    public Mono<ServerResponse> proccessMessage(ServerRequest request) {
        try {
            List<SatelliteDTO> dtos = comunicationService.findAll();
            TopSecretResponseDTO responseDTO = comunicationService.proccessMessage(comunicationService.findAll());
            return ServerResponse.ok().body(BodyInserters.fromValue(responseDTO));
        } catch (Exception e) {
            return ServerResponse.notFound().build();
        }

    }
}
