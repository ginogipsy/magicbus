package com.ginogipsy.magicbus.controller;


import com.ginogipsy.magicbus.controller.payload.request.InsertIngredientsRequest;
import com.ginogipsy.magicbus.customexception.notfound.UserNotFoundException;
import com.ginogipsy.magicbus.dto.ToppingDTO;
import com.ginogipsy.magicbus.dto.ToppingIngredientDTO;
import com.ginogipsy.magicbus.service.ToppingIngredientService;
import com.ginogipsy.magicbus.service.ToppingService;
import com.ginogipsy.magicbus.service.UserDetailsImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/topping")
public class ToppingController {

    private final ToppingService toppingService;
    private final ToppingIngredientService toppingIngredientService;

    public ToppingController(ToppingService toppingService, ToppingIngredientService toppingIngredientService) {
        this.toppingService = toppingService;
        this.toppingIngredientService = toppingIngredientService;
    }

    @PutMapping("/insert")
    @ApiOperation(value = "Insert topping", notes = "Insert a new topping")
    public ResponseEntity<ToppingDTO> insertTopping(@RequestBody @Valid final ToppingDTO toppingDTO, final @AuthenticationPrincipal UserDetailsImpl myUserDetails) {
        log.info("Inserting a new topping..");
        if (myUserDetails == null) {
            log.error("User not signed!");
            throw new UserNotFoundException("User not found!");
        }
        log.info("Inserted!");
        return ResponseEntity.ok(toppingService.insertTopping(toppingDTO, myUserDetails.getUserDTO()));
    }

    @PutMapping("/insertIngredient")
    @ApiOperation(value = "Insert ingredient for Topping", notes = "Insert a new ingredient topping")
    public ResponseEntity<ToppingDTO> insertIngredientForTopping(@RequestParam final String nameTopping,
                                                                 @RequestParam final String nameIngredient) {
        log.info("Preparing insert of ingredient " + nameIngredient + " for this topping " + nameTopping + "..");
        final ToppingIngredientDTO toppingIngredientDTO = toppingIngredientService.insertIngredient(nameTopping, nameIngredient);
        return ResponseEntity.ok(toppingIngredientDTO.getTopping());
    }

    @PutMapping("/insertIngredients")
    @ApiOperation(value = "Insert ingredient for Topping", notes = "Insert a new ingredient topping")
    public ResponseEntity<Map<String, List<String>>> insertIngredientsForTopping(@RequestBody final InsertIngredientsRequest insertIngredientsRequest) {
        log.info("Preparing insert of ingredients for this topping " + insertIngredientsRequest.getNameElement() + "..");
        final List<String> ingredients = toppingIngredientService.insertIngredients(insertIngredientsRequest.getNameElement(), insertIngredientsRequest.getIngredientList());
        final Map<String, List<String>> ingredientsAdded = new HashMap<>();
        ingredientsAdded.put("IngredientsAdded", ingredients);
        return ResponseEntity.ok(ingredientsAdded);
    }
}
