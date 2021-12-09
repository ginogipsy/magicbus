package com.ginogipsy.magicbus.component;

import com.ginogipsy.magicbus.dto.UserDTO;

public interface UserUtility {

    UserDTO reformatUserDTO(UserDTO userDTO);
    boolean isOnlyAnUser(UserDTO userDTO);

}
