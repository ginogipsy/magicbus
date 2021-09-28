package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.User;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
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
        return convertToDTO(userRepository.findByEmail(email));
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

    public UserDTO findByCodiceFiscale(String codiceFiscale){
        return Optional.ofNullable(convertToDTO(userRepository.findByCodiceFiscale(codiceFiscale))).orElse(null);
    }

    public UserDTO findByNumeroCellulare(long numeroCellullare){
        return Optional.ofNullable(convertToDTO(userRepository.findByNumeroCellulare(numeroCellullare))).orElse(null);
    }

    public UserDTO findByEmailAndUsernameAndNumeroCellulare(String email, String username, long numeroCellullare){
        return Optional.ofNullable(convertToDTO(userRepository.findByEmailAndUsernameAndNumeroCellulare(email, username, numeroCellullare))).orElse(null);
    }
}
