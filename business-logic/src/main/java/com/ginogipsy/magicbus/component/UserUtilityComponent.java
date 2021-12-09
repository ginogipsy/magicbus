package com.ginogipsy.magicbus.component;

import com.ginogipsy.magicbus.domain.enums.Profilo;
import com.ginogipsy.magicbus.dto.RoleDTO;
import com.ginogipsy.magicbus.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class UserUtilityComponent implements UserUtility {

    private final StringUtility stringUtility;

    public UserUtilityComponent(StringUtility stringUtility) {
        this.stringUtility = stringUtility;
    }

    @Override
    public UserDTO reformatUserDTO(final UserDTO userDTO) {

        Optional.ofNullable(userDTO.getNome()).ifPresent(nome -> userDTO.setNome(stringUtility.formattazionePrimaMaiusc(nome)));
        Optional.ofNullable(userDTO.getCognome()).ifPresent(cognome -> userDTO.setCognome(stringUtility.formattazionePrimaMaiusc(cognome)));
        Optional.ofNullable(userDTO.getCodiceFiscale()).ifPresent(cf -> userDTO.setCodiceFiscale(cf.toUpperCase()));
        Optional.ofNullable(userDTO.getIndirizzo()).ifPresent(ind -> userDTO.setIndirizzo(stringUtility.formattazionePrimaMaiusc(ind)));
        Optional.ofNullable(userDTO.getCitta()).ifPresent(citta -> userDTO.setCitta(stringUtility.formattataMaiuscConSpaziaturaCorretta(citta)));
        Optional.ofNullable(userDTO.getCivico()).ifPresent(civico -> userDTO.setCivico(civico.trim()));
        Optional.ofNullable(userDTO.getCap()).ifPresent(cap -> userDTO.setCap(cap.trim()));
        Optional.of(userDTO.getUsername()).ifPresent(username -> userDTO.setUsername(username.toLowerCase().trim()));
        Optional.of(userDTO.getEmail()).ifPresent(email -> userDTO.setEmail(email.toLowerCase().trim()));
        Optional.of(userDTO.getPassword()).ifPresent(password -> userDTO.setPassword(password.trim()));

        return userDTO;

    }

    @Override
    public boolean isOnlyAnUser(UserDTO userDTO) {
        return userDTO.getRoles().size() == 1 && userDTO.getRoles().stream().anyMatch(r -> r.getProfilo().equals(Profilo.getProfilo("USER")));
    }
}
