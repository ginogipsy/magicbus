package com.ginogipsy.magicbus.controller.payload.request.usercontroller;

import lombok.Data;

@Data
public class ModificaPasswordRequest {


    private String email;
    private String vecchiaPassword;
    private String nuovaPassword;
}
