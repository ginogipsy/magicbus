package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.controller.payload.request.InsertIngredientRequest;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.service.BrandService;
import com.ginogipsy.magicbus.service.IngredientService;
import com.ginogipsy.magicbus.service.UserDetailsImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;
    private final BrandService brandService;

    public IngredientController(IngredientService ingredientService, BrandService brandService) {
        this.ingredientService = ingredientService;
        this.brandService = brandService;
    }

    @PutMapping("/insert")
    @ApiOperation(value = "Insert ingredient", notes = "Insert a new ingredient")
    public ResponseEntity<IngredientDTO> insertIngredient(@RequestBody @Valid final InsertIngredientRequest insertIngredientRequest, final @AuthenticationPrincipal UserDetailsImpl myUserDetails, final BindingResult result) {
        log.info("Checking request body..");
        if (result.hasErrors()) {
            log.error("Request is not correct!");
            return ResponseEntity.badRequest().build();
        }
        log.info("Checking if user is logged..");
        if (myUserDetails == null) {
            throw new MagicbusException(BeErrorCodeEnum.USER_NOT_FOUND);
        }

        log.info("Creating a new ingredientDTO..");
        final IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setName(insertIngredientRequest.getName());
        ingredientDTO.setDescription(insertIngredientRequest.getDescription());
        Optional.ofNullable(insertIngredientRequest.getAvailable()).ifPresent(ingredientDTO::setAvailable);
        Optional.ofNullable(insertIngredientRequest.getAdditionalCostForClient()).ifPresent(ingredientDTO::setAdditionalCostForClient);
        Optional.ofNullable(insertIngredientRequest.getPurchaseCost()).ifPresent(ingredientDTO::setPurchaseCost);
        Optional.ofNullable(insertIngredientRequest.getMeasureUnit()).ifPresent(ingredientDTO::setMeasureUnit);
        Optional.ofNullable(insertIngredientRequest.getIngredientType()).ifPresent(ingredientDTO::setIngredientType);
        Optional.ofNullable(insertIngredientRequest.getAvailabilityPeriod()).ifPresent(ingredientDTO::setAvailabilityPeriod);
        Optional.ofNullable(insertIngredientRequest.getBrandName()).flatMap(bn -> Optional.ofNullable(brandService.findByName(bn))).ifPresent(ingredientDTO::setBrand);
        log.info("Inserting a new ingredientDTO..");
        log.info("Inserted!");
        return ResponseEntity.ok(ingredientService.save(ingredientDTO));
    }
}
