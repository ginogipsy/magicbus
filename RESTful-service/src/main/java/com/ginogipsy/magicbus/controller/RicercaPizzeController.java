package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.dto.GustoDTO;
import com.ginogipsy.magicbus.marshall.GustoMapper;
import com.ginogipsy.magicbus.service.GustoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/ricercaPizze")
public class RicercaPizzeController {

    private final GustoService gustoService;

    public RicercaPizzeController(GustoService gustoService) {
        this.gustoService = gustoService;
    }

    @GetMapping(value = "/ricercaGustoPerNome", params = "nomeGusto")
    public ResponseEntity<GustoDTO> ricercaPizzaSpecifica(@RequestParam String nomeGusto){
        return ResponseEntity.ok().body(gustoService.findGustoByNome(nomeGusto));
    }

    @GetMapping(value = "/ricercaPerNome")
    public ResponseEntity<List<GustoDTO>> ricercaPizzeDalNome(@RequestParam String nomeContains){
        return ResponseEntity.ok().body(gustoService.findGustiNameContains(nomeContains));
    }
}
