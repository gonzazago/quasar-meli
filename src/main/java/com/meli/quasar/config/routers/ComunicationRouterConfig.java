package com.meli.quasar.config.routers;


import com.meli.quasar.dtos.TopSecretRequestDTO;
import com.meli.quasar.dtos.TopSecretResponseDTO;
import com.meli.quasar.handler.ComunicationHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.meli.quasar.constants.StringContstants.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ComunicationRouterConfig {

    @Bean
    @RouterOperations(
            {
                    @RouterOperation(
                            path = "/api/v1/topsecret", beanClass = ComunicationHandler.class, beanMethod = "getLocation", method = RequestMethod.POST
                    ),
                    @RouterOperation(
                            path = "/api/v1/topsecret_split/", beanClass = ComunicationHandler.class, beanMethod = "proccessMessage", method = RequestMethod.GET
                    ),
                    @RouterOperation(
                            path = "/api/v1/topsecret_split/{satellite_name}", beanClass = ComunicationHandler.class, beanMethod = "proccessMessage", method = RequestMethod.POST
                    ),


            }
    )
    public RouterFunction<ServerResponse> routes(ComunicationHandler handler) {
        return route(POST(TOP_SECRET_PATH), handler::getLocation)
                .andRoute(POST(TOP_SECRET_SPLIT_POST), handler::updatePosition)
                .andRoute(GET(TOP_SECRET_SPLIT_GET), handler::proccessMessage);

    }
}
