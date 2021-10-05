package com.ginogipsy.magicbus.customexception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "il numero è già presente!")
public class CellPhoneIsPresentException extends RuntimeException{

    public CellPhoneIsPresentException(String message) {
        super(message);
    }
}
