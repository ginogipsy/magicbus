package com.ginogipsy.magicbus.component;

import com.ginogipsy.magicbus.dto.UserDTO;
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

        if(userDTO.getCivico() != null){
            userDTO.setCivico(userDTO.getCivico().trim());
        }

        if(userDTO.getCap() != null && !stringUtility.capCorretto(userDTO.getCap().trim())){
            userDTO.setCap(null);
        }else{
            userDTO.setCap(userDTO.getCap().trim());
        }
        if (userDTO.getUsername() != null){
            userDTO.setUsername(userDTO.getUsername().toLowerCase().trim());
        }
        if(userDTO.getEmail() != null){
            userDTO.setEmail(userDTO.getEmail().toLowerCase().trim());
        }

        if(userDTO.getPassword() != null){
            userDTO.setPassword(userDTO.getPassword().trim());
        }
        return userDTO;

    }
}