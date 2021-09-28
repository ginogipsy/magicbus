package com.ginogipsy.magicbus.customexception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Non ho trovato il gusto!")
public class GustoNotFoundException extends Exception {

    public GustoNotFoundException(String message){
        super(message);
    }
}
