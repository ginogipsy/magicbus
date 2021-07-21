package com.ginogipsy.magicbusV2.controller;

import com.ginogipsy.magicbusV2.dto.UserDTO;
import com.ginogipsy.magicbusV2.request.InserisciIndirizzoRequest;
import com.ginogipsy.magicbusV2.service.UserService;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/modificaUser")
    public ResponseEntity<UserDTO> modificaUser(@RequestBody UserDTO userDTO, @AuthenticationPrincipal String email){
        UserDTO user = userService.modificaUtente(email, userDTO);
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/inserisciCodiceFiscale")
    public ResponseEntity<UserDTO> inserisciCodiceFiscale(@RequestParam String codiceFiscale, @AuthenticationPrincipal String email){
        UserDTO user = userService.inserimentoCodiceFiscale(email, codiceFiscale);
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/inserisciIndirizzo")
    public ResponseEntity<UserDTO> inserisciIndirizzo(@RequestBody @Validated InserisciIndirizzoRequest inserisciIndirizzoRequest, @AuthenticationPrincipal String email){
        UserDTO user = userService.inserimentoIndirizzo(email, inserisciIndirizzoRequest.getIndirizzo(), inserisciIndirizzoRequest.getCivico(), inserisciIndirizzoRequest.getCitta(), inserisciIndirizzoRequest.getCap());
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/inserisciNomeCognome")
    public ResponseEntity<UserDTO> inserisciNomeCognome(@RequestParam String nome, @RequestParam String cognome, @AuthenticationPrincipal String email){
        UserDTO user = userService.inserimentoNomeCognome(email, nome, cognome);
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/modificaEmail")
    public ResponseEntity<UserDTO> modificaEmail(@RequestParam String nuovaEmail, @AuthenticationPrincipal String email){
        UserDTO user = userService.modificaEmail(email, nuovaEmail);
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/modificaUsername")
    public ResponseEntity<UserDTO> modificaUsername(@RequestParam String username, @AuthenticationPrincipal String email){
        UserDTO user = userService.modificaUsername(email, username);
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/modificaCellulare")
    public ResponseEntity<UserDTO> modificaCellulare(@RequestParam String numeroCellulare, @AuthenticationPrincipal String email){
        Long numCell = null;

        if(numeroCellulare.length() != 10){
            try {
                numCell = Long.parseLong(numeroCellulare);
            }catch (ConversionFailedException e){
                throw new RuntimeException("il numero deve essere composto da 10 cifre numeriche!");
            }
        }
        UserDTO user = userService.modificaNumeroCellulare(email, numCell);
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }
}
