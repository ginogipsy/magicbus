package com.ginogipsy.magicbus.customexception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "il numero non rispetta i parametri!")
public class CellPhoneNotCorrectException extends RuntimeException {

    public CellPhoneNotCorrectException(String message) {
        super(message);
    }
}
