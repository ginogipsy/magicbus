package com.ginogipsy.magicbus.service;


import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.dto.UserDTO;

import java.util.List;

public interface FrittoService {

    FriedDTO insertFritto(FriedDTO friedDTO, UserDTO userDTO);
    FriedDTO findByNome(String nomeFritto);
    List<FriedDTO> findByStatus(String status);
}
