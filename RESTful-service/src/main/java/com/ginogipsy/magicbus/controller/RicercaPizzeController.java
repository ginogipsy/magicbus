package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.dto.GustoDTO;
import com.ginogipsy.magicbus.marshall.GustoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ricercaPizze")
public class RicercaPizzeController {

    private final GustoMapper gustoMapper;

    public RicercaPizzeController(GustoMapper gustoMapper) {
        this.gustoMapper = gustoMapper;
    }

    @GetMapping("/ricercaGustoPerNome")
    public ResponseEntity<GustoDTO> ricercaPizzaDalNome(@RequestParam String nomeGusto){
        return ResponseEntity.ok().body(gustoMapper.findByNome(nomeGusto));
    }
}