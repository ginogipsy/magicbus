package com.ginogipsy.magicbus.customexception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Non ho trovato la base")
public class BaseNotFoundException extends Exception {

    public BaseNotFoundException(String message){
        super(message);
    }
}
