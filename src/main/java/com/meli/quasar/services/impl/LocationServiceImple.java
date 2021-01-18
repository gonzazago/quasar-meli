package com.meli.quasar.services.impl;

import com.meli.quasar.entities.Position;
import com.meli.quasar.entities.Satellite;
import com.meli.quasar.enums.SatelliteEnum;
import com.meli.quasar.exceptions.LocationException;
import com.meli.quasar.repository.SatelliteRepository;
import com.meli.quasar.services.LocationService;
import com.meli.quasar.utils.CalcUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class LocationServiceImple implements LocationService {

    @Autowired
    private SatelliteRepository repository;

    @Override
    public Position getLocation(List<Double> distances) {
        log.info("## Init get Location## ");
        if (distances.size() < 3) {
            throw new LocationException("The number of positions you provided does not match the number of distances");
        }
        log.info("Distances provided: {}", distances.size());
        double[] distancesArray = distances.parallelStream().mapToDouble(Double::doubleValue).toArray();
        try {
            log.info("## Invoking calcPosition ###");
            double[] positionArray = CalcUtils.calcPositition(getPositions(), distancesArray);

            return Position.builder()
                    .positionX(positionArray[0])
                    .positionY(positionArray[1])
                    .build();
        } catch (Exception e) {
            log.error("Error in getLocation", e);
            throw new LocationException("Error trying calc position");
        }
    }

    @Override
    public Position findPositionByName(String name) {
        return repository.findPositionByName(name);
    }

    private double[][] getPositions() {
        List<Satellite> satellites = new ArrayList<>();
        List<String> listPosition = new ArrayList<>();
        Arrays.asList(SatelliteEnum.values())
                .forEach(s ->
                        satellites.add(Satellite
                                .builder()
                                .name(s.getName())
                                .position(repository.findPositionByName(s.getName()))
                                .build())
                );
        satellites.forEach(e ->
                listPosition.add(
                        String.valueOf(e.getPosition().getPositionX())
                                .concat(",")
                                .concat(String.valueOf(e.getPosition().getPositionY()))
                )
        );
        double[][] positions = new double[listPosition.size()][];
        int index = 0;
        for (String s : listPosition) {
            String[] point = s.split(",");
            positions[index] = Arrays.stream(point)
                    .map(Double::valueOf)
                    .mapToDouble(Double::doubleValue)
                    .toArray();
            index++;
        }


        return positions;


    }
}
