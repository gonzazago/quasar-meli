package com.meli.quasar.services;

import com.meli.quasar.entities.Position;

import java.util.List;

public interface LocationService {

    Position getLocation(List<Double> distances);
    Position findPositionByName(String name);
}
