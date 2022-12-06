package com.ginogipsy.magicbus.controller.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author ginogipsy
 */
@Slf4j
@Data
@AllArgsConstructor
public class LoginRequest {

    @NonNull
    @NotBlank(message = "username is necessary!")
    @Size(min = 4, message = "username or password not correct")
    private String username;

    @NonNull
    @NotBlank(message = "Password is necessary!")
    @Size(min = 8, message = "username or password not correct!")
    private String password;
}
