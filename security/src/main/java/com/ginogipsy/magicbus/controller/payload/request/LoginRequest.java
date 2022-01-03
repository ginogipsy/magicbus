package com.ginogipsy.magicbus.controller.payload.request;

import lombok.Data;
import org.springframework.lang.NonNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {

    @NonNull
    @NotBlank(message = "username is necessary!")
    @Size(min = 4, message = "username or password not correct")
    private String username;

    @NonNull
    @NotBlank(message = "Name is necessary!")
    @Size(min = 8, message = "username or password not correct!")
    private String password;
}
