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
public class TasteResearchController {

    private final TasteService tasteService;

    public TasteResearchController(TasteService tasteService) {
        this.tasteService = tasteService;
    }

    @GetMapping(value = "/findByName", params = "tasteName")
    public ResponseEntity<TasteDTO> findByName(@RequestParam String tasteName){
        return ResponseEntity.ok().body(tasteService.findByName(tasteName));
    }

    @GetMapping(value = "/findInName")
    public ResponseEntity<List<TasteDTO>> findInName(@RequestParam String nameContains){
        return ResponseEntity.ok(tasteService.findTasteWhereNamesContains(nameContains));
    }

    @GetMapping(value = "/findByBase")
    public ResponseEntity<List<TasteDTO>> findByBase(@RequestParam String base){
        return ResponseEntity.ok(tasteService.findByBase(base));
    }

    @GetMapping(value = "/findByStatus")
    public ResponseEntity<List<TasteDTO>> findByStatus(@RequestParam(defaultValue = "DISPONIBILE") String status){
        return ResponseEntity.ok(tasteService.findByStatus(status));
    }

    @GetMapping(value = "/findByProductCategory")
    public ResponseEntity<List<TasteDTO>> findByProductCategory(@RequestParam String productCategory){
        return ResponseEntity.ok(tasteService.findByProductCategory(productCategory));
    }

    @GetMapping(value = "/findByAvailabilityPeriod")
    public ResponseEntity<List<TasteDTO>> findByAvailabilityPeriod(@RequestParam String availabilityPeriod){
        return ResponseEntity.ok(tasteService.findByAvailabilityPeriod(availabilityPeriod));
    }

    @GetMapping(value = "/findByAvailabilityAndStatus")
    public ResponseEntity<List<TasteDTO>> findByAvailabilityAndStatus(@RequestParam(defaultValue = "true") boolean available, @RequestParam String status){
        return ResponseEntity.ok(tasteService.findByAvailabilityAndStatus(available, status));
    }

    @GetMapping(value = "/findByAvailableAndAvailabilityPeriod")
    public ResponseEntity<List<TasteDTO>> findByAvailableAndAvailabilityPeriod(@RequestParam(defaultValue = "true") boolean available, @RequestParam String availabilityPeriod){
        return ResponseEntity.ok(tasteService.findByAvailableAndAvailabilityPeriod(available, availabilityPeriod));
    }

    @GetMapping(value = "/findByUserEntered")
    public ResponseEntity<List<TasteDTO>> findByUserEntered(@RequestParam(defaultValue = "true") boolean available){
        return ResponseEntity.ok(tasteService.findByUserEntered(available));
    }

    @GetMapping(value = "/findByUserEnteredAndStatus")
    public ResponseEntity<List<TasteDTO>> findByUserEnteredAndStatus(@RequestParam(defaultValue = "true") boolean available, @RequestParam String status){
        return ResponseEntity.ok(tasteService.findByUserEnteredAndStatus(available, status));
    }
}
