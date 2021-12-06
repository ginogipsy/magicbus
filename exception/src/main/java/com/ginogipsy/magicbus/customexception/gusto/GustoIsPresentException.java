package com.ginogipsy.magicbus.customexception.gusto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "gusto già presente!")
public class GustoIsPresentException extends RuntimeException {

    public GustoIsPresentException(String message) {
        super(message);
    }
}
