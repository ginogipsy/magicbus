package com.ginogipsy.magicbus.customexception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "email è già presente!")
public class EmailIsPresentException extends RuntimeException{

    public EmailIsPresentException(String message) {
        super(message);
    }
}
