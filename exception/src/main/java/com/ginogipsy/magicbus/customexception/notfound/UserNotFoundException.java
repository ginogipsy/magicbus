package com.ginogipsy.magicbus.customexception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "user not found!")
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "user not found!")
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
