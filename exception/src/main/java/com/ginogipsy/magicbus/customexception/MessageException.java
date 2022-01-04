package com.ginogipsy.magicbus.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT)
public class MessageException extends RuntimeException{
    public MessageException(String message){
        super(message);
    }
}
