package com.ginogipsy.magicbus.customexception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Non ho trovato la cantina!")
public class BirrificioNotFoundException extends RuntimeException{

    public BirrificioNotFoundException(String message) {
        super(message);
    }
}
