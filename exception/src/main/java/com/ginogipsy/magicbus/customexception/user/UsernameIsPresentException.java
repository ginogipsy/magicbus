package com.ginogipsy.magicbus.customexception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "username è già presente!")
public class UsernameIsPresentException extends RuntimeException{

    public UsernameIsPresentException(String message) {
        super(message);
    }
}
