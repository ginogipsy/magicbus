package com.ginogipsy.magicbus.controller.payload.request.usercontroller;

import lombok.Data;

@Data
public class AddAddressRequest {

    private String address;
    private String houseNumber;
    private String city;
    private String postalCode;
}
