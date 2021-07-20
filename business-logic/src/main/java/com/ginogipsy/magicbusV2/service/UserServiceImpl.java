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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            if(userMapper.findByCodiceFiscale(userDTO.getCodiceFiscale()) != null){
                userDTO.setCodiceFiscale(null);
            }
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            List<RoleDTO> role = new ArrayList<>();
            RoleDTO roleDTO = roleMapper.findByProfilo(Profilo.USER);
            role.add(roleDTO);
            userDTO.setRoles(role);
            userDTO.setStatus(Status.APPROVATO.toString());
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

    private UserDTO trovaUserPerEmail(String email){
        return Optional.ofNullable(email).map(userMapper::findUserByEmail).orElseThrow(RuntimeException::new);
    }

    private void modificaCredenziali(UserDTO userDaModificare, UserDTO userModificato){
        if(!userDaModificare.getEmail().equals(userModificato.getEmail()) && userMapper.findUserByEmail(userModificato.getEmail()) != null){
            throw new RuntimeException("La nuova mail è già presente!");
        }else{
            userDaModificare.setEmail(userModificato.getEmail());

        }
        if(!userDaModificare.getUsername().equals(userModificato.getUsername()) && userMapper.findUserByUsername(userModificato.getUsername()) != null){
            throw new RuntimeException("il nuovo username è già presente!");
        }else{
            userDaModificare.setUsername(userModificato.getUsername());
        }
        if(!userDaModificare.getNumeroCellulare().equals(userModificato.getNumeroCellulare()) && userMapper.findByNumeroCellulare(userModificato.getNumeroCellulare()) != null){
            throw new RuntimeException("il nuovo numero di cellulare è già presente!");
        }else{
            userDaModificare.setNumeroCellulare(userModificato.getNumeroCellulare());
        }

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
        userDaModificare.setCodiceFiscale(nuovoCodiceFiscale.toUpperCase());

    }
}
