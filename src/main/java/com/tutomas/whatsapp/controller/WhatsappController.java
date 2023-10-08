package com.tutomas.whatsapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tutomas.whatsapp.dto.MessageBodyDTO;
import com.tutomas.whatsapp.model.ResponseWhatsapp;
import com.tutomas.whatsapp.service.ApiWhatsappService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/whatsapp")
public class WhatsappController {

    private final ApiWhatsappService apiWhatsappService;

    public WhatsappController(ApiWhatsappService apiWhatsappService) {
        this.apiWhatsappService = apiWhatsappService;
    }

    @PostMapping("/enviar")
    ResponseWhatsapp enviar(@RequestBody MessageBodyDTO payload) throws JsonProcessingException {
        return apiWhatsappService.sendMessage(payload);
    }
}