package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.UserDTO;

public interface UpdatePassword {

    UserDTO updatePassword(final String email, final String oldPassword, final String newPassword);
}