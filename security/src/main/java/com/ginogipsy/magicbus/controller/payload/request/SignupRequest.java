package com.ginogipsy.magicbus.controller.payload.request;


import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class SignupRequest {

    @NonNull
    private String username;

    @NonNull
    private String email;


    @NonNull
    private String password;
    private String name;
    private String surname;

    @NonNull
    private String cellNumber;
    private String address;
    private String houseNumber;
    private String city;
    private String postalCode;
    private String fiscalCode;
    private Set<String> roles;
}
