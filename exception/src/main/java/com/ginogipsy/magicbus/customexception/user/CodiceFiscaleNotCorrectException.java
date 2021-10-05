package com.ginogipsy.magicbus.customexception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "il codice fiscale  non rispetta i parametri!")
public class CodiceFiscaleNotCorrectException extends RuntimeException {

    public CodiceFiscaleNotCorrectException(String message) {
        super(message);
    }
}
