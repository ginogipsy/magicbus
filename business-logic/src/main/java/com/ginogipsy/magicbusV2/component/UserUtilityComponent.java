package com.ginogipsy.magicbusV2.component;

import com.ginogipsy.magicbusV2.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserUtilityComponent implements UserUtility {

    private final StringUtility stringUtility;

    public UserUtilityComponent(StringUtility stringUtility) {
        this.stringUtility = stringUtility;
    }

    @Override
    public UserDTO ristruttrazioneFormattazioneUserDTO(UserDTO userDTO) {
        if(userDTO.getNome() != null){
            userDTO.setNome(stringUtility.formattazionePrimaMaiusc(userDTO.getNome()));
        }
        if(userDTO.getCognome() != null){
            userDTO.setCognome(stringUtility.formattazionePrimaMaiusc(userDTO.getCognome()));
        }
        if(userDTO.getCodiceFiscale() != null){
            if(stringUtility.controlloCodiceFiscale(userDTO.getCodiceFiscale())){
                userDTO.setCodiceFiscale(userDTO.getCodiceFiscale().toUpperCase());
            }else{
                userDTO.setCodiceFiscale(null);
            }
        }
        if(userDTO.getIndirizzo() != null){
            userDTO.setIndirizzo(stringUtility.formattazionePrimaMaiusc(userDTO.getIndirizzo()));
        }

        if(userDTO.getCitta() != null){
            userDTO.setCitta(stringUtility.formattazionePrimaMaiusc(userDTO.getCitta()));
        }
        userDTO.setUsername(userDTO.getUsername().toLowerCase().trim());
        userDTO.setEmail(userDTO.getEmail().toLowerCase().trim());
        userDTO.setPassword(userDTO.getPassword().trim());

        return userDTO;

    }
}
