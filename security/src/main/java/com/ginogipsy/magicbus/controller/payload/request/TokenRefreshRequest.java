package com.ginogipsy.magicbus.controller.payload.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * @author ginogipsy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenRefreshRequest {

    @NotBlank
    private String refreshToken;
}
