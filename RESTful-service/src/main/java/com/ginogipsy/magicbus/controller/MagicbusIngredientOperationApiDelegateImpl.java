package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.controller.model.InsertIngredientRequest;
import com.ginogipsy.magicbus.controller.model.MagicBusResult;
import com.ginogipsy.magicbus.dto.IngredientDTO;
import com.ginogipsy.magicbus.service.BrandService;
import com.ginogipsy.magicbus.service.IngredientService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import java.util.Optional;

/**
 * @author ginogipsy
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class MagicbusIngredientOperationApiDelegateImpl implements MagicbusIngredientOperationApiDelegate {

    private final BrandService brandService;
    private final IngredientService ingredientService;


    @Override
    public ResponseEntity<MagicBusResult> insertIngredient(final InsertIngredientRequest insertIngredientRequest) {
        final IngredientDTO ingredientToInsert = new IngredientDTO();
        ingredientToInsert.setName(insertIngredientRequest.getName());
        ingredientToInsert.setDescription(insertIngredientRequest.getDescription());
        Optional.ofNullable(insertIngredientRequest.getAvailable()).ifPresent(ingredientToInsert::setAvailable);

        Optional.ofNullable(insertIngredientRequest.getAdditionalCostForClient()).ifPresent(ac -> ingredientToInsert.setAdditionalCostForClient(ac.doubleValue()));
        Optional.ofNullable(insertIngredientRequest.getPurchaseCost()).ifPresent(ac -> ingredientToInsert.setPurchaseCost(ac.doubleValue()));
        Optional.ofNullable(insertIngredientRequest.getMeasureUnitEnum()).ifPresent(ingredientToInsert::setMeasureUnitEnum);
        Optional.ofNullable(insertIngredientRequest.getIngredientTypeEnum()).ifPresent(ingredientToInsert::setIngredientTypeEnum);
        Optional.ofNullable(insertIngredientRequest.getAvailabilityPeriodEnum()).ifPresent(ingredientToInsert::setAvailabilityPeriodEnum);

        Optional.ofNullable(insertIngredientRequest.getBrandName())
                .flatMap(brandService::findByName)
                .ifPresent(ingredientToInsert::setBrand);

        ingredientService.save(ingredientToInsert);
        return ResponseUtils.buildSuccessResult("Ingredient named "+insertIngredientRequest.getName()+" has been inserted correctly!");
    }
}
