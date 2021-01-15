package com.meli.quasar.unit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.quasar.dtos.SatelliteDTO;
import com.meli.quasar.dtos.TopSecretRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TestUtils {


    public static List<SatelliteDTO> getSatellites() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TopSecretRequestDTO dtos = mapper.readValue(getListSatelittesRequest(), TopSecretRequestDTO.class);
        return dtos.getSatelliteDTOS();
    }

    public static List<SatelliteDTO> getSatellitesWithOutMessageOrPosition() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TopSecretRequestDTO dtos = mapper.readValue(getListSatelittesWithOuthMessageOrDistanceRequest(), TopSecretRequestDTO.class);
        return dtos.getSatelliteDTOS();
    }

    public static List<Double> getDistances(List<SatelliteDTO>satelliteDTOS){
        return satelliteDTOS
                .parallelStream()
                .filter(s -> s.getDistance() != null)
                .map(SatelliteDTO::getDistance)
                .collect(Collectors.toList());
    }





    private static String getListSatelittesRequest() {
        return "{ \"satellites\":\n" +
                " [\n" +
                "    {   \"name\": \"kenobi\",\n" +
                "        \"distance\": 100.0,\n" +
                "        \"message\": [\"algun\", \"Mensaje\", \"\", \"\", \"\"]\n" +
                "    },\n" +
                "    {   \"name\": \"skywalker\",\n" +
                "        \"distance\": 115.5,\n" +
                "        \"message\": [\"\", \"\", \"Oculto\", \"\", \"secreto\"]\n" +
                "    },\n" +
                "    {   \"name\": \"sato\",\n" +
                "        \"distance\": 143.7,\n" +
                "        \"message\": [\"este\", \"\", \"un\", \"\", \"\"]\n" +
                "    }\n" +
                "    ]\n" +
                "    }";

    }


    private static String getListSatelittesWithOuthMessageOrDistanceRequest() {
        return "{ \"satellites\":\n" +
                " [\n" +
                "    {   \"name\": \"kenobi\",\n" +
                "        \"message\": [\"algun\", \"Mensaje\", \"\", \"\", \"\"]\n" +
                "    },\n" +
                "    {   \"name\": \"skywalker\",\n" +
                "        \"distance\": 115.5\n" +
                "    },\n" +
                "    {   \"name\": \"sato\",\n" +
                "        \"distance\": 142.7,\n" +
                "        \"message\": [\"este\", \"\", \"un\", \"\", \"\"]\n" +
                "    }\n" +
                "    ]\n" +
                "    }";

    }
}
