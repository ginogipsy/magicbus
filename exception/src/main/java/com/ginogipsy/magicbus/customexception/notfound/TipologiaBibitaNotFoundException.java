package com.ginogipsy.magicbus.customexception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Non ho trovato la tipologia di bibita!")
public class TipologiaBibitaNotFoundException extends Exception{

    public TipologiaBibitaNotFoundException(String message) {
        super(message);
    }
}
