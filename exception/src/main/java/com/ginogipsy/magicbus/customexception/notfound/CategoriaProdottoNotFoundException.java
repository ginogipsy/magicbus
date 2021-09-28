package com.ginogipsy.magicbus.customexception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Non ho trovato la categoria del prodotto!")
public class CategoriaProdottoNotFoundException extends Exception{

    public CategoriaProdottoNotFoundException(String message) {
        super(message);
    }
}
