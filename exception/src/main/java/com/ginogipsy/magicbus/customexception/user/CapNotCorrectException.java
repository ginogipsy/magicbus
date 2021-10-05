package com.ginogipsy.magicbus.customexception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "il cap  non rispetta i parametri!")
public class CapNotCorrectException extends RuntimeException{

    public CapNotCorrectException(String message) {
        super(message);
    }
}
