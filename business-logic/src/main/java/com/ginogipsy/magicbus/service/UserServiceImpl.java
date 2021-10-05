package com.ginogipsy.magicbus.service;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.component.UserUtility;
import com.ginogipsy.magicbus.customexception.notfound.UserNotFoundException;
import com.ginogipsy.magicbus.customexception.user.*;
import com.ginogipsy.magicbus.domain.Profilo;
import com.ginogipsy.magicbus.dto.RoleDTO;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final MapperFactory mapperFactory;

    private final PasswordEncoder passwordEncoder;
    private final StringUtility stringUtility;
    private final UserUtility userUtility;

    public UserServiceImpl(MapperFactory mapperFactory, PasswordEncoder passwordEncoder, StringUtility stringUtility, UserUtility userUtility) {
        this.mapperFactory = mapperFactory;
        this.passwordEncoder = passwordEncoder;
        this.stringUtility = stringUtility;
        this.userUtility = userUtility;
    }

    @Override
    public UserDTO registrazioneUtente(UserDTO userDTO) {
        userDTO = userUtility.ristruttrazioneFormattazioneUserDTO(userDTO);
        if(mapperFactory.getUserMapper().findByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail()) == null){
            if(userDTO.getCodiceFiscale() != null && mapperFactory.getUserMapper().findByCodiceFiscale(userDTO.getCodiceFiscale()) != null){
                userDTO.setCodiceFiscale(null);
            }
            if(userDTO.getNumeroCellulare().toString().length() != 10){
                throw new CellPhoneNotCorrectException("Il numero non rispetta i parametri!");
            }
            if(mapperFactory.getUserMapper().findByNumeroCellulare(userDTO.getNumeroCellulare()) != null){
                throw new CellPhoneIsPresentException("il numero è già presente");
            }

            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            Set<RoleDTO> role = new HashSet<>();
            RoleDTO roleDTO = mapperFactory.getRoleMapper().findByProfilo(Profilo.USER);
            role.add(roleDTO);
            userDTO.setRoles(role);
            userDTO.setIsEnabled(true);
            return mapperFactory.getUserMapper().save(userDTO);
        }
        throw new UsernameOrEmailArePresent("Username o email già presenti");
    }

    @Override
    public UserDTO inserimentoCodiceFiscale(UserDTO userDaModificare, String codiceFiscale) {
        if (stringUtility.controlloCodiceFiscale(codiceFiscale)) {
                aggiornamentoCodiceFiscale(userDaModificare, codiceFiscale);
                return mapperFactory.getUserMapper().save(userDaModificare);
            }

            throw new CodiceFiscaleNotCorrectException("Codice fiscale non corretto");
    }

    @Override
    public UserDTO inserimentoIndirizzo(UserDTO userDaModificare, String indirizzo, String civico, String citta, String cap){
        userDaModificare.setIndirizzo(stringUtility.formattazionePrimaMaiusc(indirizzo));
        userDaModificare.setCivico(civico);
        userDaModificare.setCitta(stringUtility.formattazionePrimaMaiusc(citta));
        if(stringUtility.capCorretto(cap)){
            userDaModificare.setCap(cap);
        }else{
            throw new CapNotCorrectException("il cap non è corretto!");
        }

        return mapperFactory.getUserMapper().save(userDaModificare);
    }

    @Override
    public UserDTO inserimentoNomeCognome(UserDTO userDaModificare, String nome, String cognome){
        userDaModificare.setNome(stringUtility.formattazionePrimaMaiusc(nome));
        userDaModificare.setCognome(stringUtility.formattazionePrimaMaiusc(cognome));
        return mapperFactory.getUserMapper().save(userDaModificare);
    }

    @Override
    public UserDTO modificaUtente(UserDTO userDaModificare, UserDTO userModificato){

        userModificato = userUtility.ristruttrazioneFormattazioneUserDTO(userModificato);

        modificaCredenziali(userDaModificare, userModificato);
        Optional.ofNullable(userModificato.getNome()).ifPresent(userDaModificare::setNome);
        Optional.ofNullable(userModificato.getCognome()).ifPresent(userDaModificare::setCognome);
        Optional.ofNullable(userModificato.getIndirizzo()).ifPresent(userDaModificare::setIndirizzo);
        Optional.ofNullable(userModificato.getCivico()).ifPresent(userDaModificare::setCivico);
        Optional.ofNullable(userModificato.getCitta()).ifPresent(userDaModificare::setCivico);
        Optional.ofNullable(userModificato.getCap()).ifPresent(cap -> {
            if(stringUtility.capCorretto(cap)) {
                userDaModificare.setCap(cap);
            }
        });

      return mapperFactory.getUserMapper().save(userDaModificare);
    }

    @Override
    public UserDTO modificaEmail(UserDTO userDaModificare, String nuovaEmail) {
            aggiornamentoEmail(userDaModificare, nuovaEmail);
            return mapperFactory.getUserMapper().save(userDaModificare);
    }

    @Override
    public UserDTO modificaUsername(UserDTO userDaModificare, String username){
        aggiornamentoUsername(userDaModificare, username);
        return mapperFactory.getUserMapper().save(userDaModificare);
    }

    @Override
    public UserDTO modificaNumeroCellulare(UserDTO userDaModificare, Long numeroCellulare) {
        aggiornamentoNumeroCellulare(userDaModificare, numeroCellulare);
        return mapperFactory.getUserMapper().save(userDaModificare);
    }

    @Override
    public UserDTO modificaPassword(String email, String username, long numeroCellulare, String nuovaPassword) {
        Optional<UserDTO> userDTO = Optional.ofNullable(mapperFactory.getUserMapper().findByEmailAndUsernameAndNumeroCellulare(email, username, numeroCellulare));
        if(userDTO.isEmpty()){
            throw new UserNotFoundException("Utente a cui modificare la password non trovato!");
        }
        userDTO.get().setPassword(passwordEncoder.encode(nuovaPassword));
        return mapperFactory.getUserMapper().save(userDTO.get());
    }

    private void modificaCredenziali(UserDTO userDaModificare, UserDTO userModificato){

        Optional.of(userModificato.getEmail()).ifPresent(email -> aggiornamentoEmail(userDaModificare, email));
        Optional.of(userModificato.getUsername()).ifPresent(username -> aggiornamentoUsername(userDaModificare, username));
        Optional.of(userModificato.getNumeroCellulare()).ifPresent(numCell -> aggiornamentoNumeroCellulare(userDaModificare, numCell));
        Optional.ofNullable(userModificato.getCodiceFiscale()).ifPresent(cf -> {
            if (stringUtility.controlloCodiceFiscale(cf)) {
                aggiornamentoCodiceFiscale(userDaModificare, cf);
            }else {
                throw new CodiceFiscaleNotCorrectException("Codice fiscale non corretto");
            }
        });

    }

    private void aggiornamentoEmail(UserDTO userDaModificare, String nuovaEmail){

        if (mapperFactory.getUserMapper().findUserByEmail(nuovaEmail) != null) {
            throw new EmailIsPresentException("La nuova mail è già presente!");
        }
        userDaModificare.setEmail(nuovaEmail.toLowerCase().trim());
    }

    private void aggiornamentoUsername(UserDTO userDaModificare, String username){
        if(mapperFactory.getUserMapper().findUserByUsername(username) != null){
            throw new UsernameIsPresentException("il nuovo username è già presente!");
        }
        userDaModificare.setUsername(username.toLowerCase().trim());
    }

    private void aggiornamentoNumeroCellulare(UserDTO userDaModificare, Long numeroCellulare){
        if(mapperFactory.getUserMapper().findByNumeroCellulare(numeroCellulare) != null){
            throw new CellPhoneIsPresentException("il nuovo numero di cellulare è già presente!");
        }
        if(numeroCellulare.toString().length() != 10) {
            throw new CellPhoneNotCorrectException("il numero di cellulare deve avere 10 cifre!");
        }
        userDaModificare.setNumeroCellulare(numeroCellulare);
    }

    private void aggiornamentoCodiceFiscale(UserDTO userDaModificare, String nuovoCodiceFiscale) {
        UserDTO userOfNewCodiceFiscale = mapperFactory.getUserMapper().findByCodiceFiscale(nuovoCodiceFiscale);

        if (userOfNewCodiceFiscale != null) {
            if (!userDaModificare.getEmail().equals(userOfNewCodiceFiscale.getEmail())) {
                throw new CodiceFiscaleIsPresentException("Codice Fiscale " + nuovoCodiceFiscale + " già presente");
            }

            userDaModificare.setCodiceFiscale(nuovoCodiceFiscale.toUpperCase().trim());
        }
    }
}
