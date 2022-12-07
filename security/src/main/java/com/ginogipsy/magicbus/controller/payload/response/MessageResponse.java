package com.ginogipsy.magicbus.controller.payload.response;

import lombok.Data;
/**
 * @author ginogipsy
 */
@Data
public class MessageResponse{

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}