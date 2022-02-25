package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.User;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class UserMapper {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserMapper(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public UserDTO findUserByUsername(final String username){
        log.info("Searching user where username is " + username+ "..");
        return Optional.ofNullable(username)
                .map(userRepository::findByUsername)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public UserDTO findById(final Integer id){
        log.info("Searching user where id is " + id + "..");
        return Optional.ofNullable(id)
                .flatMap(userRepository::findById)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public Boolean existsByUsername(final String username){
        log.info("Checking user existence where username is " + username+ "..");
        return Optional.ofNullable(username)
                .map(userRepository::existsByUsername)
                .orElse(false);
    }

    public Boolean existsByEmail(final String email){
        log.info("Checking user existence where email is " + email + "..");
        return Optional.ofNullable(email)
                .map(userRepository::existsByEmail)
                .orElse(false);
    }

    public UserDTO findUserByEmail(final String email){
        log.info("Searching user where email is " + email+ "..");
        return Optional.ofNullable(email)
                .map(userRepository::findByEmail)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public User convertToEntity(final UserDTO userDTO){
        return Optional.ofNullable(userDTO)
                .map(u -> modelMapper.map(u, User.class))
            .orElse(null);
    }

    public UserDTO convertToDTO(final User user){
        return Optional.ofNullable(user)
                .map(u -> modelMapper.map(u, UserDTO.class))
            .orElse(null);
    }

    public UserDTO save(final UserDTO userDTO){
        log.info("Saving user on db..");
        return Optional.ofNullable(userDTO)
                .map(this::convertToEntity)
                .map(userRepository::save)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public List<UserDTO> findByUsernameOrEmail(final String username, final String email){
        log.info("Searching user where username is "+username+" or email is "+email+"..");
        return Optional.ofNullable(username)
                .filter(s -> Objects.nonNull(email))
                .map(u -> userRepository.findByUsernameOrEmail(u, email)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public UserDTO findByFiscalCode(final String fiscalCode){
        log.info("Searching user where fiscalCode is " + fiscalCode + "..");
        return Optional.ofNullable(fiscalCode)
                .map(userRepository::findByFiscalCode)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public UserDTO findByCellNumber(final String cellNumber){
        log.info("Searching user where cellNumber is " + cellNumber + "..");
        return Optional.ofNullable(cellNumber)
                .map(userRepository::findByCellNumber)
                .map(this::convertToDTO)
                .orElse(null);
    }
}
