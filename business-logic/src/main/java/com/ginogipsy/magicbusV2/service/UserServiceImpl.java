package com.ginogipsy.magicbusV2.service;

import com.ginogipsy.magicbusV2.component.StringUtility;
import com.ginogipsy.magicbusV2.component.UserUtility;
import com.ginogipsy.magicbusV2.domain.Profilo;
import com.ginogipsy.magicbusV2.domain.Status;
import com.ginogipsy.magicbusV2.dto.RoleDTO;
import com.ginogipsy.magicbusV2.dto.UserDTO;
import com.ginogipsy.magicbusV2.marshall.RoleMapper;
import com.ginogipsy.magicbusV2.marshall.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final RoleMapper roleMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final StringUtility stringUtility;
    private final UserUtility userUtility;

    public UserServiceImpl(RoleMapper roleMapper, UserMapper userMapper, PasswordEncoder passwordEncoder, StringUtility stringUtility, UserUtility userUtility) {
        this.roleMapper = roleMapper;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.stringUtility = stringUtility;
        this.userUtility = userUtility;
    }

    @Override
    public UserDTO registrazioneUtente(UserDTO userDTO) {
        userDTO = userUtility.ristruttrazioneFormattazioneUserDTO(userDTO);
        if(userMapper.findByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail()) == null){
            if(userDTO.getCodiceFiscale() != null && userMapper.findByCodiceFiscale(userDTO.getCodiceFiscale()) != null){
                userDTO.setCodiceFiscale(null);
            }
            if(userDTO.getNumeroCellulare().toString().length() != 10){
                throw new RuntimeException("Il numero non rispetta i parametri!");
            }
            if(userMapper.findByNumeroCellulare(userDTO.getNumeroCellulare()) != null){
                throw new RuntimeException("il numero è già presente");
            }

            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            Set<RoleDTO> role = new HashSet<>();
            RoleDTO roleDTO = roleMapper.findByProfilo(Profilo.USER);
            role.add(roleDTO);
            userDTO.setRoles(role);
            userDTO.setIsEnabled(true);
            return userMapper.save(userDTO);
        }
        throw new RuntimeException("Username o email già presenti");
    }

    @Override
    public UserDTO inserimentoCodiceFiscale(String email, String codiceFiscale) {
        if (stringUtility.controlloCodiceFiscale(codiceFiscale)) {
            UserDTO userDaModificare = trovaUserPerEmail(email);
                aggiornamentoCodiceFiscale(userDaModificare, codiceFiscale);
                return userMapper.save(userDaModificare);
            }

            throw new RuntimeException("Codice fiscale non corretto");
    }

    @Override
    public UserDTO inserimentoIndirizzo(String email, String indirizzo, int civico, String citta, int cap){
        UserDTO userDTO = trovaUserPerEmail(email);
        userDTO.setIndirizzo(stringUtility.formattazionePrimaMaiusc(indirizzo));
        userDTO.setCivico(civico);
        userDTO.setCitta(stringUtility.formattazionePrimaMaiusc(citta));
        userDTO.setCap(cap);
        return userMapper.save(userDTO);
    }

    @Override
    public UserDTO inserimentoNomeCognome(String email, String nome, String cognome){
        UserDTO userDTO = trovaUserPerEmail(email);
        userDTO.setNome(stringUtility.formattazionePrimaMaiusc(nome));
        userDTO.setCognome(stringUtility.formattazionePrimaMaiusc(cognome));
        return userMapper.save(userDTO);
    }

    @Override
    public UserDTO modificaUtente(String email, UserDTO userModificato){
        UserDTO userDaModificare = trovaUserPerEmail(email);
        userModificato = userUtility.ristruttrazioneFormattazioneUserDTO(userModificato);

        modificaCredenziali(userDaModificare, userModificato);

        if(userModificato.getNome() != null) {userDaModificare.setNome(userModificato.getNome());}
        if(userModificato.getCognome() != null) {userDaModificare.setCognome(userModificato.getCognome());}
        if(userModificato.getIndirizzo() != null) {userDaModificare.setIndirizzo(userModificato.getIndirizzo());}
        if(userModificato.getCivico() != null) {userDaModificare.setCivico(userModificato.getCivico());}
        if(userModificato.getCitta() != null) {userDaModificare.setCitta(userModificato.getCitta());}
        if(userModificato.getCap() != null) {userDaModificare.setCap(userModificato.getCap());}

      return userMapper.save(userDaModificare);
    }

    @Override
    public UserDTO modificaEmail(String email, String nuovaEmail) {
        UserDTO userDaModificare = trovaUserPerEmail(email);
        modificaEmail(userDaModificare, nuovaEmail);
        return userMapper.save(userDaModificare);
    }

    @Override
    public UserDTO modificaUsername(String email, String username) {
        UserDTO userDaModificare = trovaUserPerEmail(email);
        modificaUsername(userDaModificare, username);
        return userMapper.save(userDaModificare);
    }

    @Override
    public UserDTO modificaNumeroCellulare(String email, Long numeroCellulare) {
        UserDTO userDaModificare = trovaUserPerEmail(email);
        modificaCellulare(userDaModificare, numeroCellulare);
        return userMapper.save(userDaModificare);
    }

    private UserDTO trovaUserPerEmail(String email){
        return Optional.ofNullable(email).map(userMapper::findUserByEmail).orElseThrow(RuntimeException::new);
    }

    private void modificaCredenziali(UserDTO userDaModificare, UserDTO userModificato){

        modificaEmail(userDaModificare, userModificato.getEmail());
        modificaUsername(userDaModificare, userModificato.getUsername());
        modificaCellulare(userDaModificare, userModificato.getNumeroCellulare());

        if(userModificato.getCodiceFiscale() != null) {
            if (stringUtility.controlloCodiceFiscale(userModificato.getCodiceFiscale())) {
                aggiornamentoCodiceFiscale(userDaModificare, userModificato.getCodiceFiscale());
            }else {
                throw new RuntimeException("Codice fiscale non corretto");
            }
        }

    }

    private void aggiornamentoCodiceFiscale(UserDTO userDaModificare, String nuovoCodiceFiscale){
        if(userDaModificare.getCodiceFiscale() != null){
            if(userDaModificare.getCodiceFiscale().equals(nuovoCodiceFiscale) && !userDaModificare.getEmail().equals(userMapper.findByCodiceFiscale(nuovoCodiceFiscale).getEmail())) {
                throw new RuntimeException("Codice Fiscale già presente");
            }else{
                if(userMapper.findByCodiceFiscale(nuovoCodiceFiscale) != null){
                    throw new RuntimeException("Codice Fiscale già presente");
                }
            }
        }else{
            if(userMapper.findByCodiceFiscale(nuovoCodiceFiscale) != null){
                throw new RuntimeException("Codice Fiscale già presente");
            }
        }
        userDaModificare.setCodiceFiscale(nuovoCodiceFiscale.toUpperCase().trim());

    }

    private void modificaEmail(UserDTO userDaModificare, String email){
        if(!userDaModificare.getEmail().equals(email) && userMapper.findUserByEmail(email) != null){
            throw new RuntimeException("La nuova mail è già presente!");
        }else{
            userDaModificare.setEmail(email.toLowerCase().trim());
        }
    }

    private void modificaUsername(UserDTO userDaModificare, String username){
        if(!userDaModificare.getUsername().equals(username) && userMapper.findUserByUsername(username) != null){
            throw new RuntimeException("il nuovo username è già presente!");
        }else{
            userDaModificare.setUsername(username.toLowerCase().trim());
        }
    }

    private void modificaCellulare(UserDTO userDaModificare, Long numeroCellulare){
        if(!userDaModificare.getNumeroCellulare().equals(numeroCellulare) && userMapper.findByNumeroCellulare(numeroCellulare) != null){
            throw new RuntimeException("il nuovo numero di cellulare è già presente!");
        }else{
            if(numeroCellulare.toString().length() == 10) {
                userDaModificare.setNumeroCellulare(numeroCellulare);
            }else{
                throw new RuntimeException("il numero di cellulare deve avere 10 cifre!");
            }
        }
    }
}
