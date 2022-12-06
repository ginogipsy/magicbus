package com.ginogipsy.magicbus.marshall;

import com.ginogipsy.magicbus.domain.User;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author ginogipsy
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

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
public Optional<User> convertToEntity(final Optional<UserDTO> userDTO){
        return Optional.ofNullable(userDTO)
                .map(u -> modelMapper.map(u, User.class));
    }

    public Optional<UserDTO> convertToDTO(final Optional<User> user){
        return Optional.ofNullable(user)
                .map(u -> modelMapper.map(u, UserDTO.class));
    }

    public Optional<UserDTO> findUserByUsername(final String username){
        log.info("UserMapper - findUserByUsername() -> Searching user where username is {}..", username);
        return Optional.ofNullable(username)
                .map(userRepository::findByUsername)
                .map(this::convertToDTO);
    }

    public Optional<UserDTO> findById(final Integer id){
        log.info("UserMapper - findById() -> Searching user where id is {}..", id);
        return Optional.ofNullable(id)
                .flatMap(userRepository::findById)
                .map(this::convertToDTO);
    }

    public boolean existsByUsername(final String username){
        log.info("UserMapper - existsByUsername() -> Checking user existence where username is {}..", username);
        return Optional.ofNullable(username)
                .map(userRepository::existsByUsername)
                .orElse(false);
    }

    public boolean existsByEmail(final String email){
        log.info("UserMapper - existsByEmail() -> Checking user existence where email is {}..", email);
        return Optional.ofNullable(email)
                .map(userRepository::existsByEmail)
                .orElse(false);
    }

    public Optional<UserDTO> findUserByEmail(final String email){
        log.info("UserMapper - findUserByEmail() -> Searching user where email is {}..", email);
        return Optional.ofNullable(email)
                .map(userRepository::findByEmail)
                .map(this::convertToDTO);
    }

    public Optional<UserDTO> save(final UserDTO userDTO){
        log.info("UserMapper - save() -> Saving user on db..");
        return Optional.ofNullable(userDTO)
                .map(this::convertToEntity)
                .map(userRepository::save)
                .map(this::convertToDTO);
    }

    public List<UserDTO> findByUsernameOrEmail(final String username, final String email){
        log.info("UserMapper - findByUsernameOrEmail() -> Searching user where username is {} or email is {}..", username, email);
        return Optional.ofNullable(username)
                .filter(s -> Objects.nonNull(email))
                .map(u -> userRepository.findByUsernameOrEmail(u, email)
                        .stream()
                        .map(this::convertToDTO)
                        .toList())
                .orElse(new ArrayList<>());
    }

    public Optional<UserDTO> findByFiscalCode(final String fiscalCode){
        log.info("UserMapper - findByFiscalCode() -> Searching user where fiscalCode is {}..", fiscalCode);
        return Optional.ofNullable(fiscalCode)
                .map(userRepository::findByFiscalCode)
                .map(this::convertToDTO);
    }

    public Optional<UserDTO> findByCellNumber(final String cellNumber){
        log.info("UserMapper - findByCellNumber() -> Searching user where cellNumber is {}..", cellNumber);
        return Optional.ofNullable(cellNumber)
                .map(userRepository::findByCellNumber)
                .map(this::convertToDTO);
    }
}
