package com.ginogipsy.magicbusV2.controller;

import com.fasterxml.jackson.databind.node.TextNode;
import com.ginogipsy.magicbusV2.dto.UserDTO;
import com.ginogipsy.magicbusV2.request.InserisciIndirizzoRequest;
import com.ginogipsy.magicbusV2.securityModel.MyUserDetails;
import com.ginogipsy.magicbusV2.service.UserService;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<UserDTO> modificaUser(@RequestBody UserDTO userDTO, @AuthenticationPrincipal MyUserDetails myUserDetails){
        UserDTO user = userService.modificaUtente(myUserDetails.getUserDTO(), userDTO);
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/inserisciCodiceFiscale")
    public ResponseEntity<UserDTO> inserisciCodiceFiscale(@RequestParam String codiceFiscale, @AuthenticationPrincipal MyUserDetails myUserDetails){
        UserDTO user = userService.inserimentoCodiceFiscale(myUserDetails.getUserDTO(), codiceFiscale);
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/inserisciIndirizzo")
    public ResponseEntity<UserDTO> inserisciIndirizzo(@RequestBody @Validated InserisciIndirizzoRequest inserisciIndirizzoRequest, @AuthenticationPrincipal MyUserDetails myUserDetails,  BindingResult bindingResult){
        UserDTO user = userService.inserimentoIndirizzo(myUserDetails.getUserDTO(), inserisciIndirizzoRequest.getIndirizzo(), inserisciIndirizzoRequest.getCivico(), inserisciIndirizzoRequest.getCitta(), inserisciIndirizzoRequest.getCap());
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/inserisciNomeCognome")
    public ResponseEntity<UserDTO> inserisciNomeCognome(@RequestParam String nome, @RequestParam String cognome, @AuthenticationPrincipal MyUserDetails myUserDetails){
        UserDTO user = userService.inserimentoNomeCognome(myUserDetails.getUserDTO(), nome, cognome);
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/modificaEmail")
    public ResponseEntity<UserDTO> modificaEmail(@RequestParam String nuovaEmail, @AuthenticationPrincipal MyUserDetails myUserDetails){
            UserDTO user = userService.modificaEmail(myUserDetails.getUserDTO(), nuovaEmail);
            return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/modificaUsername")
    public ResponseEntity<UserDTO> modificaUsername(@RequestParam String username, @AuthenticationPrincipal MyUserDetails myUserDetails){
        UserDTO user = userService.modificaUsername(myUserDetails.getUserDTO(), username);
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/modificaCellulare")
    public ResponseEntity<UserDTO> modificaCellulare(@RequestParam String numeroCellulare, @AuthenticationPrincipal MyUserDetails myUserDetails){
        Long numCell = null;

        if(numeroCellulare.length() != 10){
            try {
                numCell = Long.parseLong(numeroCellulare);
            }catch (ConversionFailedException e){
                throw new RuntimeException("il numero deve essere composto da 10 cifre numeriche!");
            }
        }
        UserDTO user = userService.modificaNumeroCellulare(myUserDetails.getUserDTO(), numCell);
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }
}
