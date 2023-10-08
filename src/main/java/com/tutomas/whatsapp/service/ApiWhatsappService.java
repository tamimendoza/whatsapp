package com.tutomas.whatsapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutomas.whatsapp.dto.MessageBodyDTO;
import com.tutomas.whatsapp.model.RequestMessage;
import com.tutomas.whatsapp.model.RequestMessageText;
import com.tutomas.whatsapp.model.ResponseWhatsapp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ApiWhatsappService {

    private final RestClient restClient;

    public ApiWhatsappService(
            @Value("${whatsapp.identificador}") String identificador,
            @Value("${whatsapp.token}") String token
    ) {
        restClient = RestClient.builder()
                .baseUrl("https://graph.facebook.com/v17.0/" + identificador + "/messages")
                .defaultHeader("Authorization", "Bearer " + token)
                .build();
    }

    public ResponseWhatsapp sendMessage(MessageBodyDTO payload) throws JsonProcessingException {
        RequestMessage request = new RequestMessage("whatsapp", payload.number(), new RequestMessageText(payload.message()));

        String response = restClient.post()
                .uri("")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(String.class);

        ObjectMapper obj = new ObjectMapper();
        return obj.readValue(response, ResponseWhatsapp.class);
    }

}
