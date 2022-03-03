package com.ginogipsy.magicbus.customexception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
//, reason = "I dati inseriti non sono corretti!" si può mettere nell'annotation
public class DataNotCorrectException extends RuntimeException{

    public DataNotCorrectException(String message) {
        super(message);
    }
}
