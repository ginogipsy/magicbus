package com.ginogipsy.magicbus.dto;


import com.ginogipsy.magicbus.domain.enums.ProfileEnum;
import lombok.Data;

@Data
public class RoleDTO {

    private int id;
    private ProfileEnum profileEnum;
}
