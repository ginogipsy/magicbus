package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.controller.model.MagicBusResult;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * @author ginogipsy
 */

public class ResponseUtils {

    private final static String CODE = "BE0000";
    private final static String DEFAULT_MESSAGE = "Operation successfully complete.";
    public static ResponseEntity<MagicBusResult> buildSuccessResult(final String customMessage) {
        final String message = Optional.ofNullable(customMessage).orElse(DEFAULT_MESSAGE);
        return ResponseEntity.ok(new MagicBusResult(CODE, message));
    }
    public static ResponseEntity<MagicBusResult> buildSuccessResult() {
        return ResponseEntity.ok(new MagicBusResult(CODE, DEFAULT_MESSAGE));
    }
}
