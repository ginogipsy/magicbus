package com.ginogipsy.magicbus.controller.payload.request;

import lombok.Data;

@Data
public class ModificaPasswordRequest {


    private String email;
    private String vecchiaPassword;
    private String nuovaPassword;
}
