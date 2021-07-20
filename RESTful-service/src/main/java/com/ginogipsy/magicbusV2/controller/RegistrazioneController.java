package com.ginogipsy.magicbusV2.controller;

import com.ginogipsy.magicbusV2.dto.UserDTO;
import com.ginogipsy.magicbusV2.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registrazione")
public class RegistrazioneController {

    private final UserService userService;

    public RegistrazioneController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registrazioneUtente")
    public ResponseEntity<UserDTO> registrazioneUtente(@RequestBody UserDTO userDTO, BindingResult result){

        if(!result.hasErrors()){
            UserDTO user =  userService.registrazioneUtente(userDTO);
            return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
        }

        return ResponseEntity.badRequest().build();

    }
}
