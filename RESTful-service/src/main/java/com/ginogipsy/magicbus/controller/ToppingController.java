package com.ginogipsy.magicbus.controller;


import com.ginogipsy.magicbus.customexception.notfound.UserNotFoundException;
import com.ginogipsy.magicbus.dto.ToppingDTO;
import com.ginogipsy.magicbus.service.TasteService;
import com.ginogipsy.magicbus.service.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topping")
public class ToppingController {

    private final TasteService tasteService;

    public ToppingController(TasteService tasteService) {
        this.tasteService = tasteService;
    }

    @PutMapping("/insert")
    public ResponseEntity<ToppingDTO> insertTaste(@RequestBody ToppingDTO toppingDTO, @AuthenticationPrincipal UserDetailsImpl myUserDetails, BindingResult result){

        if(result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        if(myUserDetails == null){
            throw new UserNotFoundException("Utente non trovato!");
        }
        return ResponseEntity.ok(tasteService.insertTaste(toppingDTO, myUserDetails.getUserDTO()));
    }
}
