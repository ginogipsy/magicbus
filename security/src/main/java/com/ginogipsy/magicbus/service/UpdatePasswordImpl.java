package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum.*;

/**
 * @author ginogipsy
 */
@Service
public class UpdatePasswordImpl implements UpdatePassword {

    private final MapperFactory mapperFactory;
    private final PasswordEncoder passwordEncoder;

    public UpdatePasswordImpl(MapperFactory mapperFactory, PasswordEncoder passwordEncoder) {
        this.mapperFactory = mapperFactory;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO updatePassword(String email, String oldPassword, String newPassword) {
        UserDTO userDTO = mapperFactory.getUserMapper().findUserByEmail(email).orElseThrow(() -> new MagicbusException(USER_NOT_FOUND));
        if(passwordEncoder.matches(oldPassword.trim(), userDTO.getPassword())){
            userDTO.setPassword(passwordEncoder.encode(newPassword.trim()));
            return mapperFactory.getUserMapper().save(userDTO)
                    .orElseThrow(() -> new MagicbusException(SAVE_FAILED));
        }
        throw new MagicbusException(PASSWORD_MISMATCH);
    }
}