package com.ginogipsy.magicbus.customexception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Non ho trovato il tipo di birra!")
public class TipologiaBirraNotFoundException extends RuntimeException{

    public TipologiaBirraNotFoundException(String message) {
        super(message);
    }
}
