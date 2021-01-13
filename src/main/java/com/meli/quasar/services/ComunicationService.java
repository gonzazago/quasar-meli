package com.meli.quasar.services;

import com.meli.quasar.dtos.SatelliteDTO;
import com.meli.quasar.dtos.TopSecretResponseDTO;

import java.util.List;

public interface ComunicationService {



     TopSecretResponseDTO proccessMessage(List<SatelliteDTO> satelliteDTOList);
}
