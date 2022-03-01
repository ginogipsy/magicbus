package com.ginogipsy.magicbus.customexception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AllergenNotFoundException extends RuntimeException {

    public AllergenNotFoundException(final String message) {
        super(message);
    }
}
