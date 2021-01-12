package com.meli.quasar.handler;

import com.meli.quasar.config.SatelliteConfig;
import com.meli.quasar.entities.Satellite;
import com.meli.quasar.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.meli.quasar.constants.StringContstants.SATELLITE_KENOBY;

@Component
public class ComunicationHandler {


    @Autowired
    private LocationService service;


    public Mono<ServerResponse> getMessage(ServerRequest request) {
        double[][] positions = new double[][]{{-500.0, -200.0}, {100.0, -100.0}, {500.0, 100.0}};
        double[] distances = new double[]{100.0, 115.5, 142.7};
        double[] expectedPosition = new double[]{-58.315252587138595, -69.55141837312165};
        service.getLocation(distances);
        return ServerResponse.ok().build();
    }
}
