package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/ingredientResearch")
public class IngredientResearchController {

    private final IngredientService ingredientService;

    public IngredientResearchController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/findByName")
    public ResponseEntity<IngredientDTO> findByName(final String name) {
        final IngredientDTO ingredientDTO = Optional.ofNullable(name)
                .map(ingredientService::findByName)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.INGREDIENT_NOT_FOUND, "It's necessary an ingredient name!"));
        return ResponseEntity.ok(ingredientDTO);
    }

    @GetMapping("/findByNameContains")
    public ResponseEntity<List<IngredientDTO>> findByNameContains(final String name) {
        final List<IngredientDTO> ingredientDTO = Optional.ofNullable(name)
                .map(ingredientService::findByNameContains)
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.INGREDIENT_NOT_FOUND, "It's necessary an ingredient name!"));
        return ResponseEntity.ok(ingredientDTO);
    }
}
