package com.ginogipsy.magicbus.controller.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
/**
 * @author ginogipsy
 */
@Getter
@Setter
public class TokenRefreshRequest {

    @NotBlank
    private String refreshToken;
}