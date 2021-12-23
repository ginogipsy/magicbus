package com.ginogipsy.magicbus.controller.payload.request;


import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class SignupRequest {

    @NonNull
    @NotBlank(message = "username is necessary!")
    @Min(value = 2, message = "A username has at least 2 characters!")
    private String username;

    @NotBlank(message = "email is necessary!")
    @Email(message = "email format is wrong!")
    private String email;


    @NonNull
    @NotBlank(message = "password is necessary!")
    @Min(value = 2, message = "A password has at least 2 characters!")
    private String password;
    private String name;
    private String surname;

    @NonNull
    @NotBlank(message = "cellNumber is necessary!")
    @Min(value = 10, message = "A cellNumber has at least 2 characters!")
    @Min(value = 13, message = "A cellNumber has max 2 characters!")
    private String cellNumber;
    private String address;
    private String houseNumber;
    private String city;
    private String postalCode;
    private String fiscalCode;
    private Set<String> roles;
}
