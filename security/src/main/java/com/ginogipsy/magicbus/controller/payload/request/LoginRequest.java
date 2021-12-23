package com.ginogipsy.magicbus.controller.payload.request;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NonNull
    @NotBlank(message = "username is necessary!")
    //@Min(value = 2, message = "A username has at least 2 characters!") -> si blocca sul minimo 2 anche se cambi valore
    private String username;

    @NonNull
    @NotBlank(message = "Name is necessary!")
    @Min(value = 2, message = "A password has at least 2 characters!")
    private String password;
}
