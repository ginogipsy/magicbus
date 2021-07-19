package com.ginogipsy.magicbusV2.marshall;

import com.ginogipsy.magicbusV2.domain.User;
import com.ginogipsy.magicbusV2.dto.UserDTO;
import com.ginogipsy.magicbusV2.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserMapper(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public UserDTO findUserByUsername(String username){
        return convertToDTO(userRepository.findByUsername(username));
    }

    public UserDTO findUserByEmail(String email){
        return convertToDTO(userRepository.findByUsername(email));
    }

    public UserDTO convertToDTO(User user){
        return (user != null) ? modelMapper.map(user, UserDTO.class) : null;
    }
}
