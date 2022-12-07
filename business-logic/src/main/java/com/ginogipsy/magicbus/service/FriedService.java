package com.ginogipsy.magicbus.service;


import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.dto.UserDTO;

import java.util.List;

/**
 * @author ginogipsy
 */

public interface FriedService {

    FriedDTO insert(final FriedDTO friedDTO, final UserDTO userDTO);
    FriedDTO findByName(final String friedName);
    List<FriedDTO> findByStatus(final String status);
}
