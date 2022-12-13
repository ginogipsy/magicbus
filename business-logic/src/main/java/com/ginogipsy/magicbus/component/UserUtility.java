package com.ginogipsy.magicbus.component;

import com.ginogipsy.magicbus.dto.UserDTO;

/**
 * @author ginogipsy
 */

public interface UserUtility {

    UserDTO reformatUserDTO(final UserDTO userDTO);
    boolean isOnlyAnUser(final UserDTO userDTO);

}
