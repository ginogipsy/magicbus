package com.ginogipsy.magicbus.controller.payload.request.usercontroller;

import lombok.Data;

@Data
public class InserisciIndirizzoRequest {

    private String indirizzo;
    private String civico;
    private String citta;
    private String cap;
}
