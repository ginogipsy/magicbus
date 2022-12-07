package com.ginogipsy.magicbus.controller.payload.response;

import lombok.Data;

import java.util.List;

/**
 * @author ginogipsy
 */
@Data
public class JwtResponse {



    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private Integer id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, String refreshToken, Integer id, String username, String email, List<String> roles) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }



}