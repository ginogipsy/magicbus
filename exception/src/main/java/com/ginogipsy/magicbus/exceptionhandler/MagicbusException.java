package com.ginogipsy.magicbus.exceptionhandler;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

/**
 * @author ginogipsy
 */

@Getter
@Setter
public class MagicbusException extends RuntimeException {

    private BeErrorCodeEnum result;
    private String customMessage;

    public MagicbusException(final BeErrorCodeEnum result) {
        this.result = result;
        this.customMessage = result.getMessage();
    }

    public MagicbusException(final BeErrorCodeEnum result, final String message) {
        this.result = result;
        this.customMessage = message;
    }

    public MagicbusException(final BeErrorCodeEnum result, final Map<String, String> attributes) {
        final String replacedMessage = StringSubstitutor.replace(result.getMessage(), attributes);
        this.result = result;
        this.customMessage = replacedMessage;
    }
}
