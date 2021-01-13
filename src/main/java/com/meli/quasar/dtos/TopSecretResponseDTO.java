package com.meli.quasar.dtos;


import com.meli.quasar.entities.Position;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TopSecretResponseDTO {

    private Position position;
    private String message;

}
