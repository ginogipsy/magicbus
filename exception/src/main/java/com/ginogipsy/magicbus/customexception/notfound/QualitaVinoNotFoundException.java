package com.ginogipsy.magicbus.customexception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Non ho trovato la qualità di vino!")
public class QualitaVinoNotFoundException extends RuntimeException{

    public QualitaVinoNotFoundException(String message) {
        super(message);
    }
}
