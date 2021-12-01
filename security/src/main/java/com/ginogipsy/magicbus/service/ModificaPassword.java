package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.UserDTO;

public interface ModificaPassword {

    UserDTO modificaPassword(final String email, final String vecchiaPassword, final String nuovaPassword);
}
