package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TokenControllerAdvice {

    @ExceptionHandler(value = MagicbusException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleTokenRefreshException(MagicbusException ex) {
        return new ErrorMessage(ex.getMessage());
    }
}