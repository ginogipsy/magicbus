package com.ginogipsy.magicbus.customexception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Non ho trovato la bibita!")
public class BibitaNotFoundException extends Exception{

    public BibitaNotFoundException(String message) {
        super(message);
    }
}
