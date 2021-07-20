package com.ginogipsy.magicbusV2.service;

import com.ginogipsy.magicbusV2.domain.Profilo;
import com.ginogipsy.magicbusV2.domain.Status;
import com.ginogipsy.magicbusV2.dto.RoleDTO;
import com.ginogipsy.magicbusV2.dto.UserDTO;
import com.ginogipsy.magicbusV2.marshall.RoleMapper;
import com.ginogipsy.magicbusV2.marshall.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final RoleMapper roleMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(RoleMapper roleMapper, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.roleMapper = roleMapper;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO registrazioneUtente(UserDTO userDTO) {

        if(userMapper.findByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail()) == null){

            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            List<RoleDTO> role = new ArrayList<>();
            RoleDTO roleDTO = roleMapper.findByProfilo(Profilo.USER);
            role.add(roleDTO);
            userDTO.setRoles(role);
            userDTO.setStatus(Status.APPROVATO.toString());
            return userMapper.save(userDTO);
        }
        throw new RuntimeException();
    }
}
