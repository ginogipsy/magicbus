package com.ginogipsy.magicbusV2.service;

import com.ginogipsy.magicbusV2.domain.Profilo;
import com.ginogipsy.magicbusV2.domain.Status;
import com.ginogipsy.magicbusV2.dto.UserDTO;
import com.ginogipsy.magicbusV2.marshall.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO registrazioneUtente(UserDTO userDTO) {

        if(userMapper.findByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail()) == null){

            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userDTO.setProfilo(Profilo.USER.toString());
            userDTO.setStatus(Status.APPROVATO.toString());
            return userMapper.save(userDTO);
        }
        throw new RuntimeException();
    }
}
