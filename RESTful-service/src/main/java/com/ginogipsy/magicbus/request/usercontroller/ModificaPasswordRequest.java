package com.ginogipsy.magicbus.request.usercontroller;

import lombok.Data;

@Data
public class ModificaPasswordRequest {


    private String email;
    private String vecchiaPassword;
    private String nuovaPassword;
}
