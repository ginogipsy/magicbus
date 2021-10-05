package com.ginogipsy.magicbus.customexception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "codice fiscale è già presente!")
public class CodiceFiscaleIsPresentException extends RuntimeException{

    public CodiceFiscaleIsPresentException(String message) {
        super(message);
    }
}
