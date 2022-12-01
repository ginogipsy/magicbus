package com.ginogipsy.magicbus.service;


import com.ginogipsy.magicbus.dto.UserDTO;

import java.util.Optional;

public interface UserService {

    UserDTO signUpUser(UserDTO userDTO);
    UserDTO addFiscalCode(UserDTO oldUser, String fiscalCode);
    UserDTO addAddress(UserDTO oldUser, String street, String houseNumber, String city, String postalCode);
    UserDTO addNameAndSurname(UserDTO oldUser, String name, String surname);
    UserDTO updateUser(UserDTO oldUser, UserDTO updatedUser);
    UserDTO updateEmail(UserDTO oldUser, String newEmail);
    UserDTO updateUsername(UserDTO oldUser, String username);
    UserDTO updateCellNumber(UserDTO oldUser, String newCellNumber);
    UserDTO findByEmail(String email);
    Optional<UserDTO> findByUsername(final String username);

}
