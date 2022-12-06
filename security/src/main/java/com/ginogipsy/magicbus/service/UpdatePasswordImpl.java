package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum.PASSWORD_MISMATCH;
import static com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum.USER_NOT_FOUND;

/**
 * @author ginogipsy
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdatePasswordImpl implements UpdatePassword {

    private final MapperFactory mapperFactory;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO updatePassword(final String email, final String oldPassword, final String newPassword) {

        log.info("UpdatePasswordImpl - updatePassword() -> Updating password where user email is '{}' ..", email);
        final UserDTO userDTO = mapperFactory.getUserMapper()
                .findUserByEmail(email)
                .orElseThrow(() -> new MagicbusException(USER_NOT_FOUND));

        if(passwordEncoder.matches(oldPassword.trim(), userDTO.getPassword())){

            log.info("UpdatePasswordImpl - updatePassword() -> Updating password because they match ..");
            userDTO.setPassword(passwordEncoder.encode(newPassword.trim()));

            return mapperFactory.getUserMapper().save(userDTO)
                    .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.SAVE_FAILED));
        }

        log.error("UpdatePasswordImpl - updatePassword() -> Updating password failed - password mismatch!");
        throw new MagicbusException(PASSWORD_MISMATCH);
    }
}
