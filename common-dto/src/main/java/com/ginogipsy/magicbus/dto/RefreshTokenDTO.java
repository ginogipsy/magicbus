package com.ginogipsy.magicbus.dto;

import lombok.Data;

import java.time.Instant;

/**
 * @author ginogipsy
 */

@Data
public class RefreshTokenDTO {

    private Long id;
    private UserDTO user;
    private String token;
    private Instant expiryDate;
}
