package com.ginogipsy.magicbus.controller.payload.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}
