package com.ginogipsy.magicbus.request.usercontroller;

import lombok.Data;

@Data
public class ModificaPasswordRequest {


    private String email;
    private String username;
    private long numeroCellulare;
    private String nuovaPassword;
}