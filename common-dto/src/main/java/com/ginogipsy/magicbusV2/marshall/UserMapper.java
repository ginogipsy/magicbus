package com.ginogipsy.magicbusV2.marshall;

import com.ginogipsy.magicbusV2.domain.User;
import com.ginogipsy.magicbusV2.dto.UserDTO;
import com.ginogipsy.magicbusV2.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public User convertToEntity(UserDTO userDTO){
        return (userDTO != null) ? modelMapper.map(userDTO, User.class) : null;
    }

    public UserDTO convertToDTO(User user){
        return (user != null) ? modelMapper.map(user, UserDTO.class) : null;
    }

    public UserDTO save(UserDTO userDTO){
        return convertToDTO(userRepository.save(convertToEntity(userDTO)));
    }

    public List<UserDTO> findByUsernameOrEmail(String username, String email){
        List<User> users = userRepository.findByUsernameOrEmail(username, email);
        return (!users.isEmpty()) ? users.stream().map(this::convertToDTO).collect(Collectors.toList()) : null;
    }
}
