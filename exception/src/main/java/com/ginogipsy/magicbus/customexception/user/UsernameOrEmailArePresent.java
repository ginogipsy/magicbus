package com.ginogipsy.magicbus.customexception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "username o email sono già presenti!")
public class UsernameOrEmailArePresent extends RuntimeException{

    public UsernameOrEmailArePresent(String message) {
        super(message);
    }
}
