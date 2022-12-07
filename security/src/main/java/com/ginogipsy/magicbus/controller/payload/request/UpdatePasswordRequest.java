package com.ginogipsy.magicbus.controller.payload.request;

import com.ginogipsy.magicbus.constraint.ValidPassword;
import lombok.Data;

import javax.validation.constraints.Email;
/**
 * @author ginogipsy
 */
@Data
public class UpdatePasswordRequest {


    @Email
    private String email;
    @ValidPassword(message = "Password should have 8 characters with a lower char, upper char, number and symbol!")
    private String oldPassword;
    @ValidPassword(message = "Password should have 8 characters with a lower char, upper char, number and symbol!")
    private String newPassword;
}