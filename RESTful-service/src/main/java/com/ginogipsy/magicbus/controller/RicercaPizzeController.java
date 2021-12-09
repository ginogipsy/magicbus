package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.dto.TasteDTO;
import com.ginogipsy.magicbus.service.TasteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ricercaPizze")
public class RicercaPizzeController {

    private final TasteService tasteService;

    public RicercaPizzeController(TasteService tasteService) {
        this.tasteService = tasteService;
    }

    @GetMapping(value = "/ricercaGustoPerNome", params = "nomeGusto")
    public ResponseEntity<TasteDTO> ricercaPizzaSpecifica(@RequestParam String nomeGusto){
        return ResponseEntity.ok().body(tasteService.findByName(nomeGusto));
    }

    @GetMapping(value = "/ricercaPerNome")
    public ResponseEntity<List<TasteDTO>> ricercaPizzeDalNome(@RequestParam String nomeContains){
        return ResponseEntity.ok(tasteService.findTasteWhereNamesContains(nomeContains));
    }

    @GetMapping(value = "/findByBase")
    public ResponseEntity<List<TasteDTO>> findByBase(@RequestParam String base){
        return ResponseEntity.ok(tasteService.findByBase(base));
    }

    @GetMapping(value = "/findGustiByStatus")
    public ResponseEntity<List<TasteDTO>> findGustiByStatus(@RequestParam(defaultValue = "DISPONIBILE") String status){
        return ResponseEntity.ok(tasteService.findByStatus(status));
    }

    @GetMapping(value = "/findByCategoriaProdotto")
    public ResponseEntity<List<TasteDTO>> findByCategoriaProdotto(@RequestParam String categoriaProdotto){
        return ResponseEntity.ok(tasteService.findByProductCategory(categoriaProdotto));
    }

    @GetMapping(value = "/findByPeriodoDisponibilita")
    public ResponseEntity<List<TasteDTO>> findByPeriodoDisponibilita(@RequestParam String periodoDisponibilita){
        return ResponseEntity.ok(tasteService.findByAvailabilityPeriod(periodoDisponibilita));
    }

    @GetMapping(value = "/findByDisponibilita")
    public ResponseEntity<List<TasteDTO>> findByDisponibilita(@RequestParam(defaultValue = "true") boolean disponibile, @RequestParam String disponibilita){
        return ResponseEntity.ok(tasteService.findByAvailabilityAndStatus(disponibile, disponibilita));
    }

    @GetMapping(value = "/findByDisponibilitaAndPeriodoDisponibilita")
    public ResponseEntity<List<TasteDTO>> findByDisponibilitaAndPeriodoDisponibilita(@RequestParam(defaultValue = "true") boolean disponibile, @RequestParam String periodoDisponibilita){
        return ResponseEntity.ok(tasteService.findByDisponibilitaAndPeriodoDisponibilita(disponibile, periodoDisponibilita));
    }

    @GetMapping(value = "/findByInseritaDaUtente")
    public ResponseEntity<List<TasteDTO>> findByInseritaDaUtente(@RequestParam(defaultValue = "true") boolean disponibile){
        return ResponseEntity.ok(tasteService.findByInseritaDaUtente(disponibile));
    }

    @GetMapping(value = "/findByInseritaDaUtenteAndStatus")
    public ResponseEntity<List<TasteDTO>> findByInseritaDaUtenteAndStatus(@RequestParam(defaultValue = "true") boolean disponibile, @RequestParam String status){
        return ResponseEntity.ok(tasteService.findByInseritaDaUtenteAndStatus(disponibile, status));
    }
}
