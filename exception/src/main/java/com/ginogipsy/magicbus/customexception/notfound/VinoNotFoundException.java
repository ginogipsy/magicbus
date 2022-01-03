package com.ginogipsy.magicbus.customexception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Vino not found!")
public class VinoNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public VinoNotFoundException(String message) {
        super(message);
    }
}
