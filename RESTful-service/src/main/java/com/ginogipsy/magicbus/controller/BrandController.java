package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.component.StringUtility;
import com.ginogipsy.magicbus.dto.BrandDTO;
import com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.service.BrandService;
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

@Slf4j
@RestController
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandService brandService;
    private final StringUtility stringUtility;

    public BrandController(BrandService brandService, StringUtility stringUtility) {
        this.brandService = brandService;
        this.stringUtility = stringUtility;
    }

    @PutMapping("/insert")
    @ApiOperation(value = "Insert fried", notes = "Insert a fried")
    public ResponseEntity<BrandDTO> insertBrand(@RequestBody final BrandDTO brandDTO, final @AuthenticationPrincipal UserDetailsImpl myUserDetails, final BindingResult result) {
        log.info("Checking request body..");
        if (result.hasErrors()) {
            log.error("Request is not correct!");
            return ResponseEntity.badRequest().build();
        }
        log.info("Checking if user is logged..");
        if (myUserDetails == null) {
            throw new MagicbusException(BeErrorCodeEnum.USER_NOT_FOUND);
        }
        log.info("Inserting new fried..");
        return ResponseEntity.ok(brandService.insert(brandDTO));
    }
}
