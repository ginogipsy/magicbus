package com.ginogipsy.magicbus.component;

import com.ginogipsy.magicbus.domain.enums.Profile;
import com.ginogipsy.magicbus.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author ginogipsy
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class UserUtilityComponent implements UserUtility {

    private final StringUtility stringUtility;

    @Override
    public UserDTO reformatUserDTO(final UserDTO userDTO) {

        log.info("UserUtilityComponent - reformatUserDTO() -> reformat this user's fields ..");
        Optional.ofNullable(userDTO.getName()).ifPresent(name -> userDTO.setName(stringUtility.formatWithFirstUpper(name)));
        Optional.ofNullable(userDTO.getSurname()).ifPresent(surname -> userDTO.setSurname(stringUtility.formatWithFirstUpper(surname)));
        Optional.ofNullable(userDTO.getFiscalCode()).ifPresent(cf -> userDTO.setFiscalCode(cf.toUpperCase()));
        Optional.ofNullable(userDTO.getAddress()).ifPresent(street -> userDTO.setAddress(stringUtility.formatWithFirstUpper(street)));
        Optional.ofNullable(userDTO.getCity()).ifPresent(city -> userDTO.setCity(stringUtility.formatWithFirstUpper(city)));
        Optional.ofNullable(userDTO.getHouseNumber()).ifPresent(houseNum -> userDTO.setHouseNumber(houseNum.trim()));
        Optional.ofNullable(userDTO.getPostalCode()).ifPresent(postalCode -> userDTO.setPostalCode(postalCode.trim()));
        Optional.ofNullable(userDTO.getUsername()).ifPresent(username -> userDTO.setUsername(username.toLowerCase().trim()));
        Optional.ofNullable(userDTO.getEmail()).ifPresent(email -> userDTO.setEmail(email.toLowerCase().trim()));
        Optional.ofNullable(userDTO.getPassword()).ifPresent(password -> userDTO.setPassword(password.trim()));

        return userDTO;

    }

    @Override
    public boolean isOnlyAnUser(final UserDTO userDTO) {
        log.info("UserUtilityComponent - isOnlyAnUser() -> verify if user named {} has only USER role ..", userDTO.getUsername());
        return userDTO.getRoles().size() == 1 && userDTO.getRoles()
                .stream()
                .anyMatch(r -> r.getProfile().equals(Profile.getProfile("USER")));
    }
}
