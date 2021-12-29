package com.ginogipsy.magicbus.controller.payload.request.usercontroller;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddAddressRequest {

    @NotBlank(message = "It is necessary a new address!")
    private String address;

    @NotBlank(message = "It is necessary a new house number!")
    private String houseNumber;
    private String city;
    private String postalCode;
}
