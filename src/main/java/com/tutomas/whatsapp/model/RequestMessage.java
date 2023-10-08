package com.tutomas.whatsapp.model;

public record RequestMessage(
        String messaging_product,
        String to,
        RequestMessageText text
) {
}
