package com.meli.quasar.services.impl;

import com.meli.quasar.dtos.SatelliteDTO;
import com.meli.quasar.dtos.TopSecretResponseDTO;
import com.meli.quasar.entities.Position;
import com.meli.quasar.entities.Satellite;
import com.meli.quasar.exceptions.ComunicationException;
import com.meli.quasar.mappers.SatelliteMapper;
import com.meli.quasar.services.ComunicationService;
import com.meli.quasar.services.LocationService;
import com.meli.quasar.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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
    @Qualifier(KENOBI)
    private Satellite kenoby;
    @Autowired
    @Qualifier(SATELLITE_SKYWALKER)
    private Satellite skywalker;

    @Autowired
    @Qualifier(SATELLITE_SATO)
    private Satellite sato;

    @Autowired
    @Qualifier(SATELLITES_MAP)
    private Map<String, Satellite> satelliteMap;
    @Autowired
    @Qualifier(SATELLITES_LIST)
    private List<Satellite> satellites;

    SatelliteMapper mapper;
    private double[][] positions;

    @PostConstruct
    public void loadDistances() {
        this.positions = new double[][]{
                {kenoby.getPosition().getPositionX(), kenoby.getPosition().getPositionY()},
                {skywalker.getPosition().getPositionX(), skywalker.getPosition().getPositionY()},
                {sato.getPosition().getPositionX(), sato.getPosition().getPositionY()}
        };
        mapper = Mappers.getMapper(SatelliteMapper.class);
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

    @Override
    public List<SatelliteDTO> findAll() {
        return mapper.toDtoList(satellites);
    }

    @Override
    public void updateSatellite(String name, SatelliteDTO request) {
        if (satelliteMap.containsKey(name)) {
            switch (name) {
                case KENOBI:
                    kenoby.setDistance(request.getDistance());
                    kenoby.setMessage(request.getMessage());
                    break;
                case SATELLITE_SATO:
                    sato.setDistance(request.getDistance());
                    sato.setMessage(request.getMessage());
                    break;
                case SATELLITE_SKYWALKER:
                    skywalker.setDistance(request.getDistance());
                    skywalker.setMessage(request.getMessage());
                    break;
                default:
                    throw new NoSuchElementException("Can't get satellite for name: " + name);
            }
        } else {
            throw new ComunicationException("No exist satellite with name: " + name);
        }
    }

}


