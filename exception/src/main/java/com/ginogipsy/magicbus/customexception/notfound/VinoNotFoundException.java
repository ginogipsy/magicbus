package com.ginogipsy.magicbus.customexception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Non ho trovato il vino!")
public class VinoNotFoundException extends RuntimeException {

    public VinoNotFoundException(String message) {
        super(message);
    }
}
