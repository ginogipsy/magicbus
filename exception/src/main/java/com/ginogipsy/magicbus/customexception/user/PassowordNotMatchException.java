package com.ginogipsy.magicbus.customexception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.LOCKED, reason = "La vecchia password non corrisponde")
public class PassowordNotMatchException extends RuntimeException{

    public PassowordNotMatchException(String message) {
        super(message);
    }
}
