package com.meli.quasar.services;

import com.meli.quasar.entities.Position;

import java.util.List;

public interface LocationService {

    Position getLocation(double[][] positions,List<Double> distances);
}
