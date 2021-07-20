package com.ginogipsy.magicbusV2.controller;

import com.ginogipsy.magicbusV2.dto.UserDTO;
import com.ginogipsy.magicbusV2.request.InserisciIndirizzoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @PutMapping("/modificaUser")
    public ResponseEntity<UserDTO> modificaUser(@RequestBody UserDTO userDTO){
        UserDTO user = null;
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/inserisciCodiceFiscale")
    public ResponseEntity<UserDTO> inserisciCodiceFiscale(@RequestParam String codiceFiscale){
        UserDTO user = null;
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/inserisciIndirizzo")
    public ResponseEntity<UserDTO> inserisciIndirizzo(@RequestBody @Validated InserisciIndirizzoRequest inserisciIndirizzoRequest){
        UserDTO user = null;
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }
}
