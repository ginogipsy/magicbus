package com.ginogipsy.magicbus.service;


import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * @author ginogipsy
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MapperFactory mapperFactory;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        log.info("UserDetailsServiceImpl - loadUserByUsername() -> Creating refresh token where username is '{}' ..", username);

        final UserDTO userDTO = mapperFactory.getUserMapper()
                .findUserByUsername(username)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.USER_NOT_FOUND, "User Not Found with username: " + username));

        return UserDetailsImpl.build(userDTO);
    }
}
