package com.ginogipsy.magicbus.dto;


import com.ginogipsy.magicbus.domain.enums.Profile;
import lombok.Data;

@Data
public class RoleDTO {

    private int id;
    private Profile profile;
}
