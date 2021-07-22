package com.ginogipsy.magicbusV2.service;


import com.ginogipsy.magicbusV2.dto.UserDTO;

public interface UserService {

    UserDTO registrazioneUtente(UserDTO userDTO);
    UserDTO inserimentoCodiceFiscale(UserDTO userDaModificare, String codiceFiscale);
    UserDTO inserimentoIndirizzo(UserDTO userDaModificare, String indirizzo, String civico, String citta, String cap);
    UserDTO inserimentoNomeCognome(UserDTO userDaModificare, String nome, String cognome);
    UserDTO modificaUtente(UserDTO userDaModificare, UserDTO userModificato);
    UserDTO modificaEmail(UserDTO userDaModificare, String nuovaEmail);
    UserDTO modificaUsername(UserDTO userDaModificare, String username);
    UserDTO modificaNumeroCellulare(UserDTO userDaModificare, Long numeroCellulare);
    UserDTO modificaPassword(String email, String username, long numeroCellulare, String nuovaPassword);
}
