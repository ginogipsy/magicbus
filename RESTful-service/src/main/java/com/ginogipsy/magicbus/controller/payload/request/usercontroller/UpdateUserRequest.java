package com.ginogipsy.magicbus.controller.payload.request.usercontroller;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class UpdateUserRequest {

    @Email(message = "It is necessary an email format!")
    private String email;

    @Min(value = 4, message = "Username must be at least 4 characters long!")
    private String username;
    private String name;
    private String surname;

    @Size(min = 9, max = 11, message = "Cell number must have 9 to 11 digits!")
    private String cellNumber;
    private String address;
    private String houseNumber;
    private String city;
    private String postalCode;
    private String fiscalCode;
}
