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
    public UserDTO registrazioneUtente(final UserDTO userDTO) {
        final UserDTO user = userUtility.ristruttrazioneFormattazioneUserDTO(userDTO);
        if(mapperFactory.getUserMapper().findByUsernameOrEmail(user.getUsername(), user.getEmail()) == null){
            if(user.getCodiceFiscale() != null && mapperFactory.getUserMapper().findByCodiceFiscale(user.getCodiceFiscale()) != null){
                user.setCodiceFiscale(null);
            }
            if(user.getNumeroCellulare().toString().length() != 10){
                throw new CellPhoneNotCorrectException("Il numero non rispetta i parametri!");
            }
            if(mapperFactory.getUserMapper().findByNumeroCellulare(user.getNumeroCellulare()) != null){
                throw new CellPhoneIsPresentException("il numero è già presente");
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Set<RoleDTO> role = new HashSet<>();
            RoleDTO roleDTO = mapperFactory.getRoleMapper().findByProfilo(Profilo.USER);
            role.add(roleDTO);
            user.setRoles(role);
            user.setIsEnabled(true);
            return mapperFactory.getUserMapper().save(user);
        }
        throw new UsernameOrEmailArePresent("Username o email già presenti");
    }

    @Override
    public UserDTO inserimentoCodiceFiscale(final UserDTO userDaModificare,final String codiceFiscale) {
        if (stringUtility.controlloCodiceFiscale(codiceFiscale)) {
                aggiornamentoCodiceFiscale(userDaModificare, codiceFiscale);
                return mapperFactory.getUserMapper().save(userDaModificare);
            }

            throw new CodiceFiscaleNotCorrectException("Codice fiscale non corretto");
    }

    @Override
    public UserDTO inserimentoIndirizzo(final UserDTO userDaModificare,final String indirizzo,final String civico,final String citta,final String cap){
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
    public UserDTO inserimentoNomeCognome(final UserDTO userDaModificare,final String nome,final String cognome){
        userDaModificare.setNome(stringUtility.formattazionePrimaMaiusc(nome));
        userDaModificare.setCognome(stringUtility.formattazionePrimaMaiusc(cognome));
        return mapperFactory.getUserMapper().save(userDaModificare);
    }

    @Override
    public UserDTO modificaUtente(final UserDTO userDaModificare,final UserDTO userModificato){

        final UserDTO user = userUtility.ristruttrazioneFormattazioneUserDTO(userModificato);

        modificaCredenziali(userDaModificare, user);
        Optional.ofNullable(user.getNome()).ifPresent(userDaModificare::setNome);
        Optional.ofNullable(user.getCognome()).ifPresent(userDaModificare::setCognome);
        Optional.ofNullable(user.getIndirizzo()).ifPresent(userDaModificare::setIndirizzo);
        Optional.ofNullable(user.getCivico()).ifPresent(userDaModificare::setCivico);
        Optional.ofNullable(user.getCitta()).ifPresent(userDaModificare::setCivico);
        Optional.ofNullable(user.getCap()).ifPresent(cap -> {
            if(stringUtility.capCorretto(cap)) {
                userDaModificare.setCap(cap);
            }
        });

      return mapperFactory.getUserMapper().save(userDaModificare);
    }

    @Override
    public UserDTO modificaEmail(final UserDTO userDaModificare,final String nuovaEmail) {
            aggiornamentoEmail(userDaModificare, nuovaEmail);
            return mapperFactory.getUserMapper().save(userDaModificare);
    }

    @Override
    public UserDTO modificaUsername(final UserDTO userDaModificare,final String username){
        aggiornamentoUsername(userDaModificare, username);
        return mapperFactory.getUserMapper().save(userDaModificare);
    }

    @Override
    public UserDTO modificaNumeroCellulare(final UserDTO userDaModificare, final Long numeroCellulare) {
        aggiornamentoNumeroCellulare(userDaModificare, numeroCellulare);
        return mapperFactory.getUserMapper().save(userDaModificare);
    }

    @Override
    public UserDTO modificaPassword(final String email, final String vecchiaPassword, final String nuovaPassword) {
        UserDTO userDTO = Optional.ofNullable(mapperFactory.getUserMapper().findUserByEmail(email)).orElseThrow(() -> new UserNotFoundException("Utente a cui modificare la password non trovato!"));
        if(passwordEncoder.matches(vecchiaPassword, userDTO.getPassword())){
            userDTO.setPassword(passwordEncoder.encode(nuovaPassword));
            return mapperFactory.getUserMapper().save(userDTO);
        }
        throw new PassowordNotMatchException("La password non corrisponde!");

    }

    private void modificaCredenziali(final UserDTO userDaModificare,final UserDTO userModificato){

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

    private void aggiornamentoEmail(final UserDTO userDaModificare, final String nuovaEmail){
        if (mapperFactory.getUserMapper().findUserByEmail(nuovaEmail) != null) {
            throw new EmailIsPresentException("La nuova mail è già presente!");
        }
        userDaModificare.setEmail(nuovaEmail.toLowerCase().trim());
    }

    private void aggiornamentoUsername(final UserDTO userDaModificare, final String username){
        if(mapperFactory.getUserMapper().findUserByUsername(username) != null){
            throw new UsernameIsPresentException("il nuovo username è già presente!");
        }
        userDaModificare.setUsername(username.toLowerCase().trim());
    }

    private void aggiornamentoNumeroCellulare(final UserDTO userDaModificare,final Long numeroCellulare){
        if(mapperFactory.getUserMapper().findByNumeroCellulare(numeroCellulare) != null){
            throw new CellPhoneIsPresentException("il nuovo numero di cellulare è già presente!");
        }
        if(numeroCellulare.toString().length() != 10) {
            throw new CellPhoneNotCorrectException("il numero di cellulare deve avere 10 cifre!");
        }
        userDaModificare.setNumeroCellulare(numeroCellulare);
    }

    private void aggiornamentoCodiceFiscale(final UserDTO userDaModificare,final String nuovoCodiceFiscale) {
        UserDTO userOfNewCodiceFiscale = mapperFactory.getUserMapper().findByCodiceFiscale(nuovoCodiceFiscale);

        if (userOfNewCodiceFiscale != null) {
            if (!userDaModificare.getEmail().equals(userOfNewCodiceFiscale.getEmail())) {
                throw new CodiceFiscaleIsPresentException("Codice Fiscale " + nuovoCodiceFiscale + " già presente");
            }

            userDaModificare.setCodiceFiscale(nuovoCodiceFiscale.toUpperCase().trim());
        }
    }
}
