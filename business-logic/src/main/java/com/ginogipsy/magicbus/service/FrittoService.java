package com.ginogipsy.magicbus.service;


import com.ginogipsy.magicbus.dto.FrittoDTO;
import com.ginogipsy.magicbus.dto.UserDTO;

import java.util.List;

public interface FrittoService {

    FrittoDTO insertFritto(FrittoDTO frittoDTO, UserDTO userDTO);
    FrittoDTO findByNome(String nomeFritto);
    List<FrittoDTO> findByStatus(String status);
}
