package com.meli.quasar.services.impl;

import com.meli.quasar.dtos.SatelliteDTO;
import com.meli.quasar.dtos.TopSecretResponseDTO;
import com.meli.quasar.entities.Position;
import com.meli.quasar.entities.Satellite;
import com.meli.quasar.enums.SatelliteEnum;
import com.meli.quasar.exceptions.ComunicationException;
import com.meli.quasar.mappers.SatelliteMapper;
import com.meli.quasar.services.ComunicationService;
import com.meli.quasar.services.LocationService;
import com.meli.quasar.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommunicationServiceImple implements ComunicationService {

    @Autowired
    private LocationService locationService;

    @Autowired
    private MessageService messageService;


    private Map<String, Satellite> satelliteMap;


    @Autowired
    SatelliteMapper mapper;

    @PostConstruct
    public void loadDistances() {

        satelliteMap = new HashMap<>();
        Arrays.asList(SatelliteEnum.values())
                .forEach(s ->
                        satelliteMap.put(s.getName(), Satellite
                                .builder()
                                .name(s.getName())
                                .position(s.getPosition())
                                .build())
                );

    }

    @Override
    public TopSecretResponseDTO proccessMessage(List<SatelliteDTO> satelliteDTOList) {
        List<Double> distances = satelliteDTOList
                .parallelStream()
                .map(SatelliteDTO::getDistance)
                .collect(Collectors.toList());
        try {
            Position position = locationService.getLocation(distances);
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
        List<Satellite> satellites = new ArrayList<>();
        satelliteMap.forEach((k, v) -> satellites.add(v));

        return mapper.toDtoList(satellites);
    }

    @Override
    public void updateSatellite(String name, SatelliteDTO request) {
        if (satelliteMap.containsKey(name)) {
            Satellite s = satelliteMap.get(name);
            s.setDistance(request.getDistance());
            s.setMessage(request.getMessage());
            satelliteMap.put(name, s);
        } else {
            throw new ComunicationException("No exist satellite with name: " + name);
        }
    }

}


