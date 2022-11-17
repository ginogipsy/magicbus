package com.ginogipsy.magicbus.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

/**
 * @author ginogipsy
 */

@Slf4j
@RestControllerAdvice
@Primary
public class ExceptionManager {

    @ExceptionHandler(MagicbusException.class)
    public ResponseEntity<Error> magicbusException(final MagicbusException magicbusException) {
        log.error("ExceptionHandler - magicbusException - message: {}", magicbusException.getCustomMessage());
        magicbusException.printStackTrace();
        return buildError(magicbusException);
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<Error> exception(final Exception ex) {
        log.error("ExceptionHandler - genericException - message: {}", ex.getMessage());
        ex.printStackTrace();
        return buildGenericError(ex.getMessage());
    }

    private ResponseEntity<Error> buildError(final MagicbusException magicbusException) {
        final Error error = Optional.ofNullable(magicbusException.getResult())
                .map(e -> new Error(e.getCode(), magicbusException.getCustomMessage()))
                .orElse(new Error(BeErrorCodeEnum.GENERIC_ERROR.getCode(), BeErrorCodeEnum.GENERIC_ERROR.getMessage()));

        final HttpStatus httpStatus = Optional.ofNullable(magicbusException.getResult())
                .map(BeErrorCodeEnum::getHttpStatus)
                .orElse(BeErrorCodeEnum.GENERIC_ERROR.getHttpStatus());

        return ResponseEntity.status(httpStatus).body(error);

    }

    private ResponseEntity<Error> buildGenericError(final String message) {
        final Error error = new Error(BeErrorCodeEnum.GENERIC_ERROR.getCode(), Optional.ofNullable(message).orElse(BeErrorCodeEnum.GENERIC_ERROR.getMessage()));
        return ResponseEntity.status(BeErrorCodeEnum.GENERIC_ERROR.getHttpStatus()).body(error);
    }

    @Data
    @AllArgsConstructor
    static class Error {
        private String code;
        private String message;
    }
}
