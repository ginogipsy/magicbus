package com.ginogipsy.magicbus.controller.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class JwtResponse {

    private String jwt;
    private Integer id;
    private String username;
    private String email;
    private List<String> roles;


    public JwtResponse(String jwt, Integer id, String username, String email, List<String> roles) {
        this.jwt = jwt;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
