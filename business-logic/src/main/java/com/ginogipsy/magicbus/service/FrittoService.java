package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.FrittoDTO;
import com.ginogipsy.magicbus.dto.UserDTO;

public interface FrittoService {

    FrittoDTO insertFritto(FrittoDTO frittoDTO, UserDTO userDTO);
}
