package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.dto.FrittoDTO;
import com.ginogipsy.magicbus.service.FrittoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/friedResearch")
public class FriedResearchController {

    private final FrittoService frittoService;

    public FriedResearchController(FrittoService frittoService) {
        this.frittoService = frittoService;
    }

    @GetMapping(value = "/byName", params = "friedName")
    public ResponseEntity<FrittoDTO> ricercaPizzaSpecifica(@RequestParam String friedName){
        return ResponseEntity.ok().body(frittoService.findByNome(friedName));
    }

    @GetMapping(value = "/byStatus")
    public ResponseEntity<List<FrittoDTO>> findGustiByStatus(@RequestParam(defaultValue = "DISPONIBILE") String status){
        return ResponseEntity.ok(frittoService.findByStatus(status));
    }
}