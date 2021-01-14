package com.meli.quasar.config.routers;


import com.meli.quasar.handler.ComunicationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.meli.quasar.constants.StringContstants.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ComunicationRouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(ComunicationHandler handler) {
        return route(POST(TOP_SECRET_PATH), handler::getLocation)
                .andRoute(POST(TOP_SECRET_SPLIT_POST), handler::updatePosition)
                .andRoute(GET(TOP_SECRET_SPLIT_GET), handler::proccessMessage);

    }
}
