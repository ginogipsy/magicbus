package com.ginogipsy.magicbus.component;

import com.ginogipsy.magicbus.domain.enums.Profile;
import com.ginogipsy.magicbus.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserUtilityComponent implements UserUtility {

    private final StringUtility stringUtility;

    public UserUtilityComponent(StringUtility stringUtility) {
        this.stringUtility = stringUtility;
    }

    @Override
    public UserDTO reformatUserDTO(final UserDTO userDTO) {

        Optional.ofNullable(userDTO.getName()).ifPresent(name -> userDTO.setName(stringUtility.formatWithFirstMaiusc(name)));
        Optional.ofNullable(userDTO.getSurname()).ifPresent(surname -> userDTO.setSurname(stringUtility.formatWithFirstMaiusc(surname)));
        Optional.ofNullable(userDTO.getFiscalCode()).ifPresent(cf -> userDTO.setFiscalCode(cf.toUpperCase()));
        Optional.ofNullable(userDTO.getAddress()).ifPresent(street -> userDTO.setAddress(stringUtility.formatWithFirstMaiusc(street)));
        Optional.ofNullable(userDTO.getCity()).ifPresent(city -> userDTO.setCity(stringUtility.formatWithFirstMaiusc(city)));
        Optional.ofNullable(userDTO.getHouseNumber()).ifPresent(houseNum -> userDTO.setHouseNumber(houseNum.trim()));
        Optional.ofNullable(userDTO.getPostalCode()).ifPresent(postalCode -> userDTO.setPostalCode(postalCode.trim()));
        Optional.ofNullable(userDTO.getUsername()).ifPresent(username -> userDTO.setUsername(username.toLowerCase().trim()));
        Optional.ofNullable(userDTO.getEmail()).ifPresent(email -> userDTO.setEmail(email.toLowerCase().trim()));
        Optional.ofNullable(userDTO.getPassword()).ifPresent(password -> userDTO.setPassword(password.trim()));

        return userDTO;

    }

    @Override
    public boolean isOnlyAnUser(UserDTO userDTO) {
        return userDTO.getRoles().size() == 1 && userDTO.getRoles().stream().anyMatch(r -> r.getProfile().equals(Profile.getProfile("USER")));
    }
}
