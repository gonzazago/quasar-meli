package com.meli.quasar.services.impl;

import com.meli.quasar.entities.Satellite;
import com.meli.quasar.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.meli.quasar.constants.StringContstants.*;

@Service
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
    public double[] getLocation(double... distances) {

        return new double[0];
    }
}
