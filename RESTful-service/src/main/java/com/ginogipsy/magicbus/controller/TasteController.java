package com.ginogipsy.magicbus.controller;


import com.ginogipsy.magicbus.customexception.notfound.UserNotFoundException;
import com.ginogipsy.magicbus.dto.TasteDTO;
import com.ginogipsy.magicbus.service.TasteService;
import com.ginogipsy.magicbus.service.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gusto")
public class TasteController {

    private final TasteService tasteService;

    public TasteController(TasteService tasteService) {
        this.tasteService = tasteService;
    }

    @PutMapping("/insert")
    public ResponseEntity<TasteDTO> insertTaste(@RequestBody TasteDTO tasteDTO, @AuthenticationPrincipal UserDetailsImpl myUserDetails, BindingResult result){

        if(result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        if(myUserDetails == null){
            throw new UserNotFoundException("Utente non trovato!");
        }
        return ResponseEntity.ok(tasteService.insertTaste(tasteDTO, myUserDetails.getUserDTO()));
    }
}
