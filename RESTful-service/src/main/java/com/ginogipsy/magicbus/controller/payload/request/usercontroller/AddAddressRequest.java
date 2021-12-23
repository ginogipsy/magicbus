package com.ginogipsy.magicbus.controller.payload.request.usercontroller;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddAddressRequest {

    @NotBlank
    private String address;
    private String houseNumber;
    private String city;
    private String postalCode;
}
