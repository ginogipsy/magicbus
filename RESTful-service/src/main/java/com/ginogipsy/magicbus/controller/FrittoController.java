package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.customexception.notfound.UserNotFoundException;
import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.service.FrittoService;
import com.ginogipsy.magicbus.service.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fritto")
public class FrittoController {

    private final FrittoService frittoService;

    public FrittoController(FrittoService frittoService) {
        this.frittoService = frittoService;
    }

    @PutMapping("/insert")
    public ResponseEntity<FriedDTO> insertFritto(@RequestBody FriedDTO friedDTO, @AuthenticationPrincipal UserDetailsImpl myUserDetails, BindingResult result){

        if(result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        if(myUserDetails == null){
            throw new UserNotFoundException("Utente non trovato!");
        }
        return ResponseEntity.ok(frittoService.insertFritto(friedDTO, myUserDetails.getUserDTO()));
    }
}
