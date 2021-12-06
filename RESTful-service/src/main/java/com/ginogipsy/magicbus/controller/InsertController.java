package com.ginogipsy.magicbus.controller;


import com.ginogipsy.magicbus.dto.GustoDTO;
import com.ginogipsy.magicbus.service.GustoService;
import com.ginogipsy.magicbus.service.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/insert")
public class InsertController {

    private final GustoService gustoService;

    public InsertController(GustoService gustoService) {
        this.gustoService = gustoService;
    }

    @PutMapping("/gusto")
    public ResponseEntity<GustoDTO> insertGusto(@RequestBody GustoDTO gustoDTO, @AuthenticationPrincipal UserDetailsImpl myUserDetails, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(gustoService.insertGusto(gustoDTO, myUserDetails.getUserDTO()));
    }
}
