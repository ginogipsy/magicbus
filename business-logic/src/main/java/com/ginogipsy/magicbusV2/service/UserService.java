package com.ginogipsy.magicbusV2.service;


import com.ginogipsy.magicbusV2.dto.UserDTO;

public interface UserService {

    UserDTO registrazioneUtente(UserDTO userDTO);
    UserDTO inserimentoCodiceFiscale(String email, String codiceFiscale);
    UserDTO inserimentoIndirizzo(String email, String indirizzo, int civico, String citta, int cap);
    UserDTO inserimentoNomeCognome(String email, String nome, String cognome);
    UserDTO modificaUtente(String email, UserDTO userModificato);
    UserDTO modificaEmail(String email, String nuovaEmail);
    UserDTO modificaUsername(String email, String username);
    UserDTO modificaNumeroCellulare(String email, Long numeroCellulare);
}
