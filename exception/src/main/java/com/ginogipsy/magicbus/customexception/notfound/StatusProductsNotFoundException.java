package com.ginogipsy.magicbus.customexception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Non ho trovato lo status indicato!")
public class StatusProductsNotFoundException extends RuntimeException {

    public StatusProductsNotFoundException(String message){
        super(message);
    }
}
