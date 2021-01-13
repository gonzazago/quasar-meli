package com.meli.quasar.services;

import com.meli.quasar.dtos.SatelliteDTO;
import com.meli.quasar.entities.Satellite;

import java.util.List;

public interface MessageService {

    String getMessage(List<SatelliteDTO> satellites);
}
