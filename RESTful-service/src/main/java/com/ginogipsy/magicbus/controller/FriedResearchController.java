package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.service.FriedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/friedResearch")
public class FriedResearchController {

    private final FriedService friedService;

    public FriedResearchController(FriedService friedService) {
        this.friedService = friedService;
    }

    @GetMapping(value = "/byName", params = "friedName")
    public ResponseEntity<FriedDTO> findByName(@RequestParam String friedName){
        return ResponseEntity.ok().body(friedService.findByName(friedName));
    }

    @GetMapping(value = "/byStatus")
    public ResponseEntity<List<FriedDTO>> findByStatus(@RequestParam(defaultValue = "DISPONIBILE") String status){
        return ResponseEntity.ok(friedService.findByStatus(status));
    }
}
