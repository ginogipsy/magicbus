package com.ginogipsy.magicbus.customexception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Non ho trovato il periodo della disponibilità!")
public class PeriodoDisponibilitaNotFoundException extends Exception{

    public PeriodoDisponibilitaNotFoundException(String message) {
        super(message);
    }
}
