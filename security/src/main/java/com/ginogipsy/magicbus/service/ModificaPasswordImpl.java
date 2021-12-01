package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.customexception.notfound.UserNotFoundException;
import com.ginogipsy.magicbus.customexception.user.PassowordNotMatchException;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModificaPasswordImpl implements ModificaPassword{

    private final MapperFactory mapperFactory;
    private final PasswordEncoder passwordEncoder;

    public ModificaPasswordImpl(MapperFactory mapperFactory, PasswordEncoder passwordEncoder) {
        this.mapperFactory = mapperFactory;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO modificaPassword(String email, String vecchiaPassword, String nuovaPassword) {
        UserDTO userDTO = Optional.ofNullable(mapperFactory.getUserMapper().findUserByEmail(email)).orElseThrow(() -> new UserNotFoundException("Utente a cui modificare la password non trovato!"));
        if(passwordEncoder.matches(vecchiaPassword.trim(), userDTO.getPassword())){
            userDTO.setPassword(passwordEncoder.encode(nuovaPassword.trim()));
            return mapperFactory.getUserMapper().save(userDTO);
        }
        throw new PassowordNotMatchException("La password non corrisponde!");
    }
}
