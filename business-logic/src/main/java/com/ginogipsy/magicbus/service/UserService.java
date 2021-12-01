package com.ginogipsy.magicbus.service;


import com.ginogipsy.magicbus.dto.UserDTO;

public interface UserService {

    UserDTO registrazioneUtente(UserDTO userDTO);
    UserDTO inserimentoCodiceFiscale(UserDTO userDaModificare, String codiceFiscale);
    UserDTO inserimentoIndirizzo(UserDTO userDaModificare, String indirizzo, String civico, String citta, String cap);
    UserDTO inserimentoNomeCognome(UserDTO userDaModificare, String nome, String cognome);
    UserDTO modificaUtente(UserDTO userDaModificare, UserDTO userModificato);
    UserDTO modificaEmail(UserDTO userDaModificare, String nuovaEmail);
    UserDTO modificaUsername(UserDTO userDaModificare, String username);
    UserDTO modificaNumeroCellulare(UserDTO userDaModificare, String numeroCellulare);

}
