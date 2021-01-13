package com.meli.quasar.services.impl;

import com.meli.quasar.entities.Position;
import com.meli.quasar.entities.Satellite;
import com.meli.quasar.exceptions.LocationException;
import com.meli.quasar.services.LocationService;
import com.meli.quasar.utils.CalcUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.meli.quasar.constants.StringContstants.*;

@Service
@Slf4j
public class LocationServiceImple implements LocationService {

    @Autowired
    @Qualifier(SATELLITE_KENOBY)
    private Satellite kenoby;
    @Autowired
    @Qualifier(SATELLITE_SKYWALKER)
    private Satellite skywalker;

    @Autowired
    @Qualifier(SATELLITE_SATO)
    private Satellite sato;

    @Override
    public Position getLocation(List<Double> distances) {
        log.info("## Init get Location## ");

        if (distances.size() < 3) {
            throw new LocationException("The number of positions you provided does not match the number of distances");
        }

        log.info("Distances provided: {}", distances.size());
        double[] distancesArray = distances.parallelStream().mapToDouble(Double::doubleValue).toArray();
        double[][] positions = new double[][]{
                {kenoby.getPosition().getPositionX(), kenoby.getPosition().getPositionY()},
                {skywalker.getPosition().getPositionX(), skywalker.getPosition().getPositionY()},
                {sato.getPosition().getPositionX(), sato.getPosition().getPositionY()}
        };

        try {
            log.info("## Invoking calcPosition ###");
            double[] positionArray = CalcUtils.calcPositition(positions, distancesArray);

            return Position.builder()
                    .positionX(positionArray[0])
                    .positionY(positionArray[1])
                    .build();
        } catch (Exception e) {
            log.error("Error in getLocation", e);
            throw new LocationException("Error trying calc position");
        }
    }
}
