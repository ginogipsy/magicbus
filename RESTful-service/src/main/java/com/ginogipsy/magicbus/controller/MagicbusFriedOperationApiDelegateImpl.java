package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.controller.model.AddIngredientsToFriedRequest;
import com.ginogipsy.magicbus.controller.model.InsertFriedRequest;
import com.ginogipsy.magicbus.controller.model.MagicBusResult;
import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.service.FriedIngredientService;
import com.ginogipsy.magicbus.service.FriedService;
import com.ginogipsy.magicbus.service.UserService;
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
public class MagicbusFriedOperationApiDelegateImpl implements MagicbusFriedOperationApiDelegate{

    private final FriedService friedService;
    private final FriedIngredientService friedIngredientService;
    private final UserService userService;
    private final StringUtility stringUtility;
    @Override
    public ResponseEntity<MagicBusResult> addIngredientsToFried(AddIngredientsToFriedRequest addIngredientsToFriedRequest) {
        return MagicbusFriedOperationApiDelegate.super.addIngredientsToFried(addIngredientsToFriedRequest);
    }

    @Override
    public ResponseEntity<MagicBusResult> insertFried(InsertFriedRequest insertFriedRequest) {
        final UserDTO user = userService.findByUsername(insertFriedRequest.getUsernameCreator())
                .orElseThrow(() -> new MagicbusException(BeErrorCodeEnum.USER_NOT_FOUND));

        final FriedDTO friedToInsert = new FriedDTO();
        friedToInsert.setName(insertFriedRequest.getFriedName());
        friedToInsert.setFriedDescription(insertFriedRequest.getDescription());
        //Optional.ofNullable(insertFriedRequest.getStatus()).ifPresent(friedDTO::setStatus);
        Optional.ofNullable(insertFriedRequest.getCost()).ifPresent(c -> friedToInsert.setCost(c.doubleValue()));
        //Optional.ofNullable(insertFriedRequest.getVeganOption()).ifPresent(friedDTO::setVeganOption);
        Optional.ofNullable(insertFriedRequest.getAvailable()).ifPresent(friedToInsert::setAvailable);
        //Optional.ofNullable(insertFriedRequest.getProductCategory()).ifPresent(friedDTO::setProductCategory);

        friedService.insert(friedToInsert, user);
        return ResponseUtils.buildSuccessResult("Fried named "+insertFriedRequest.getFriedName()+" has been inserted correctly!");
    }
}
