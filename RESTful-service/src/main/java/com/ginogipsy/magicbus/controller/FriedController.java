package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.controller.payload.request.InsertIngredientsRequest;
import com.ginogipsy.magicbus.customexception.notfound.UserNotFoundException;
import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.dto.FriedIngredientDTO;
import com.ginogipsy.magicbus.service.FriedIngredientService;
import com.ginogipsy.magicbus.service.FriedService;
import com.ginogipsy.magicbus.service.UserDetailsImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/fried")
public class FriedController {

    private final FriedService friedService;
    private final FriedIngredientService friedIngredientService;
    private final StringUtility stringUtility;

    public FriedController(FriedService friedService, FriedIngredientService friedIngredientService, StringUtility stringUtility) {
        this.friedService = friedService;
        this.friedIngredientService = friedIngredientService;
        this.stringUtility = stringUtility;
    }

    @PutMapping("/insert")
    @ApiOperation(value = "Insert fried", notes = "Insert a fried")
    public ResponseEntity<FriedDTO> insertFried(@RequestBody FriedDTO friedDTO, @AuthenticationPrincipal UserDetailsImpl myUserDetails, BindingResult result) {
    log.info("Checking request body..");
        if(result.hasErrors()){
            log.error("Request is not correct!");
            return ResponseEntity.badRequest().build();
        }
        log.info("Checking if user is logged..");
        if (myUserDetails == null) {
            throw new UserNotFoundException("User not found!");
        }
        log.info("Inserting new fried..");
        return ResponseEntity.ok(friedService.insertFried(friedDTO, myUserDetails.getUserDTO()));
    }

    @PutMapping("/insertIngredient")
    @ApiOperation(value = "Insert ingredient for fried", notes = "Insert a new ingredient fried")
    public ResponseEntity<FriedDTO> insertIngredientForFried(@RequestParam final String nameFried, @RequestParam final String nameIngredient) {
        log.info("Preparing insert of ingredient " + nameIngredient + " for this fried " + nameFried + "..");
        final FriedIngredientDTO friedIngredientDTO = friedIngredientService.insertIngredient(nameFried, nameIngredient);
        return ResponseEntity.ok(friedIngredientDTO.getFried());
    }

    @PutMapping("/insertIngredients")
    @ApiOperation(value = "Insert ingredient for fried", notes = "Insert a new ingredient fried")
    public ResponseEntity<Map<String, List<String>>> insertIngredientsForFried(@RequestBody final InsertIngredientsRequest insertIngredientsRequest, @AuthenticationPrincipal UserDetailsImpl myUserDetails, BindingResult result) {
        if (result.hasErrors()) {
            log.error("Request is not correct!");
            return ResponseEntity.badRequest().build();
        }
        log.info("Checking if user is logged..");
        if (myUserDetails == null) {
            throw new UserNotFoundException("User not found!");
        }
        log.info("Preparing insert of ingredients for this fried " + insertIngredientsRequest.getNameElement() + "..");
        final List<String> ingredients = friedIngredientService.insertIngredients(insertIngredientsRequest.getNameElement(), insertIngredientsRequest.getIngredientList());
        final Map<String, List<String>> ingrediensAdded = new HashMap<>();
        ingrediensAdded.put("IngredientsAdded", ingredients);
        return ResponseEntity.ok(ingrediensAdded);
    }
}
