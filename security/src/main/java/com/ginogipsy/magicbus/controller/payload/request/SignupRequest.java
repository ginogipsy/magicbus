package com.ginogipsy.magicbus.controller.payload.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class SignupRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
    private String name;
    private String surname;

    @NotBlank
    private String cellNumber;
    private String address;
    private String houseNumber;
    private String city;
    private String postalCode;
    private String fiscalCode;
    private Set<String> roles;
}
