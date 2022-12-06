package com.ginogipsy.magicbus.controller.payload.request;


import com.ginogipsy.magicbus.constraint.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author ginogipsy
 */
@Data
@AllArgsConstructor
public class SignupRequest {

    @NonNull
    @NotBlank(message = "username is necessary!")
    @Size(min = 4, message = "A username has at least 4 characters!")
    private String username;

    @NotBlank(message = "email is necessary!")
    @Email(message = "email format is wrong!")
    private String email;

    @NonNull
    @NotBlank(message = "password is necessary!")
    @ValidPassword(message = "Password should have 8 characters with a lower char, upper char, number and symbol!")
    private String password;
    private String name;
    private String surname;

    @NonNull
    @NotBlank(message = "cellNumber is necessary!")
    @Size(min = 9, max = 11, message = "Cell number must have 9 to 11 digits!")
    private String cellNumber;
    private String address;
    private String houseNumber;
    private String city;
    private String postalCode;
    private String fiscalCode;
    private Set<String> roles;
}
