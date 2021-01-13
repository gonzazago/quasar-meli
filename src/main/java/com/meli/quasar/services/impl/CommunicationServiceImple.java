package com.meli.quasar.services.impl;

import com.meli.quasar.dtos.SatelliteDTO;
import com.meli.quasar.dtos.TopSecretResponseDTO;
import com.meli.quasar.entities.Position;
import com.meli.quasar.entities.Satellite;
import com.meli.quasar.exceptions.ComunicationException;
import com.meli.quasar.services.ComunicationService;
import com.meli.quasar.services.LocationService;
import com.meli.quasar.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

import static com.meli.quasar.constants.StringContstants.*;

@Service
@Slf4j
public class CommunicationServiceImple implements ComunicationService {

    @Autowired
    private LocationService locationService;

    @Autowired
    private MessageService messageService;

    @Autowired
    @Qualifier(SATELLITE_KENOBY)
    private Satellite kenoby;
    @Autowired
    @Qualifier(SATELLITE_SKYWALKER)
    private Satellite skywalker;

    @Autowired
    @Qualifier(SATELLITE_SATO)
    private Satellite sato;

    private double[][] positions;

    @PostConstruct
    public void loadDistances() {
        this.positions = new double[][]{
                {kenoby.getPosition().getPositionX(), kenoby.getPosition().getPositionY()},
                {skywalker.getPosition().getPositionX(), skywalker.getPosition().getPositionY()},
                {sato.getPosition().getPositionX(), sato.getPosition().getPositionY()}
        };
    }

    @Override
    public TopSecretResponseDTO proccessMessage(List<SatelliteDTO> satelliteDTOList) {
        List<Double> distances = satelliteDTOList
                .parallelStream()
                .map(SatelliteDTO::getDistance)
                .collect(Collectors.toList());
        try {
            Position position = locationService.getLocation(positions, distances);
            String message = messageService.getMessage(satelliteDTOList);
            return TopSecretResponseDTO.builder()
                    .position(position)
                    .message(message)
                    .build();
        } catch (Exception e) {
            log.error("Error trying intercerpt the message", e);
            throw new ComunicationException("No possible intercept the message");
        }

    }
}
