package com.meli.quasar.services.impl;

import com.meli.quasar.dtos.SatelliteDTO;
import com.meli.quasar.entities.Satellite;
import com.meli.quasar.services.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public String getMessage(List<SatelliteDTO> satellites) {

        String[][] messages = new String[satellites.size()][];

        AtomicInteger index = new AtomicInteger(0);

        satellites.forEach(satellite -> {
            if (satellite.getMessage() == null) {
                throw new IllegalArgumentException("El satelite " + satellite.getName() + " no posee información (Arreglo del mensaje) para leer/armar el mensaje final.");
            }
            messages[index.getAndIncrement()] = satellite.getMessage();
        });

        return getMessage(messages);
    }


    public String getMessage(String[]... messages) {

        StringBuilder builder = new StringBuilder();

        int wordsCount = messages[0].length;

        for (String[] messageArray : messages) {
            if (wordsCount != messageArray.length) {
                throw new IllegalArgumentException("El tamaño del arreglo del mensaje no es igual para todos los satelites: " + wordsCount + " != " + messageArray.length);
            }
        }

        for (int i = 0; i < messages[0].length; i++) {
            for (int j = 0; j < messages.length; j++) {
                if (messages[j][i] != null && !messages[j][i].isEmpty()) {
                    builder.append(messages[j][i].trim());
                    if (i != messages[0].length - 1) {
                        builder.append(" ");
                    }
                    break;
                }
            }
        }

        return builder.toString();
    }
}
