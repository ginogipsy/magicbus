package com.ginogipsy.magicbus.controller.payload.request;

import com.ginogipsy.magicbus.constraint.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

/**
 * @author ginogipsy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequest {


    @Email
    private String email;
    @ValidPassword(message = "Password should have 8 characters with a lower char, upper char, number and symbol!")
    private String oldPassword;
    @ValidPassword(message = "Password should have 8 characters with a lower char, upper char, number and symbol!")
    private String newPassword;
}
