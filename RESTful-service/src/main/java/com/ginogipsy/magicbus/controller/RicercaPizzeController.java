package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.dto.GustoDTO;
import com.ginogipsy.magicbus.service.GustoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        return ResponseEntity.ok(gustoService.findGustiNameContains(nomeContains));
    }

    @GetMapping(value = "/findByBase")
    public ResponseEntity<List<GustoDTO>> findByBase(@RequestParam String base){
        return ResponseEntity.ok(gustoService.findByBase(base));
    }

    @GetMapping(value = "/findGustiByStatus")
    public ResponseEntity<List<GustoDTO>> findGustiByStatus(@RequestParam(defaultValue = "DISPONIBILE") String status){
        return ResponseEntity.ok(gustoService.findGustiByStatus(status));
    }

    @GetMapping(value = "/findByCategoriaProdotto")
    public ResponseEntity<List<GustoDTO>> findByCategoriaProdotto(@RequestParam String categoriaProdotto){
        return ResponseEntity.ok(gustoService.findByCategoriaProdotto(categoriaProdotto));
    }

    @GetMapping(value = "/findByPeriodoDisponibilita")
    public ResponseEntity<List<GustoDTO>> findByPeriodoDisponibilita(@RequestParam String periodoDisponibilita){
        return ResponseEntity.ok(gustoService.findByPeriodoDisponibilita(periodoDisponibilita));
    }

    @GetMapping(value = "/findByDisponibilita")
    public ResponseEntity<List<GustoDTO>> findByDisponibilita(@RequestParam(defaultValue = "true") boolean disponibile, @RequestParam String disponibilita){
        return ResponseEntity.ok(gustoService.findByDisponibilita(disponibile, disponibilita));
    }

    @GetMapping(value = "/findByDisponibilitaAndPeriodoDisponibilita")
    public ResponseEntity<List<GustoDTO>> findByDisponibilitaAndPeriodoDisponibilita(@RequestParam(defaultValue = "true") boolean disponibile, @RequestParam String periodoDisponibilita){
        return ResponseEntity.ok(gustoService.findByDisponibilitaAndPeriodoDisponibilita(disponibile, periodoDisponibilita));
    }

    @GetMapping(value = "/findByInseritaDaUtente")
    public ResponseEntity<List<GustoDTO>> findByInseritaDaUtente(@RequestParam(defaultValue = "true") boolean disponibile){
        return ResponseEntity.ok(gustoService.findByInseritaDaUtente(disponibile));
    }

    @GetMapping(value = "/findByInseritaDaUtenteAndStatus")
    public ResponseEntity<List<GustoDTO>> findByInseritaDaUtenteAndStatus(@RequestParam(defaultValue = "true") boolean disponibile, @RequestParam String status){
        return ResponseEntity.ok(gustoService.findByInseritaDaUtenteAndStatus(disponibile, status));
    }
}
