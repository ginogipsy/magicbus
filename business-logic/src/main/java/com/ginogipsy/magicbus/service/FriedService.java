package com.ginogipsy.magicbus.service;


import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.dto.UserDTO;

import java.util.List;

public interface FriedService {

    FriedDTO insertFried(FriedDTO friedDTO, UserDTO userDTO);
    FriedDTO findByName(String friedName);
    List<FriedDTO> findByStatus(String status);
}
