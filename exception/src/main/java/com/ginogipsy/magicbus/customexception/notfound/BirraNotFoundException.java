package com.ginogipsy.magicbus.customexception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Non ho trovato la birra!")
public class BirraNotFoundException extends Exception{

    public BirraNotFoundException(String message) {
        super(message);
    }
}