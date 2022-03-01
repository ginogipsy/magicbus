package com.ginogipsy.magicbus.controller;


import com.ginogipsy.magicbus.customexception.notfound.UserNotFoundException;
import com.ginogipsy.magicbus.dto.ToppingDTO;
import com.ginogipsy.magicbus.service.ToppingService;
import com.ginogipsy.magicbus.service.UserDetailsImpl;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/topping")
public class ToppingController {

    private final ToppingService toppingService;

    public ToppingController(ToppingService toppingService) {
        this.toppingService = toppingService;
    }

    @PutMapping("/insert")
    @ApiOperation(value = "Insert topping", notes = "Insert a new topping")
    public ResponseEntity<ToppingDTO> insertTaste(@RequestBody @Valid final ToppingDTO toppingDTO, final @AuthenticationPrincipal UserDetailsImpl myUserDetails) {
        log.info("Inserting a new taste..");
        if (myUserDetails == null) {
            log.error("User not signed!");
            throw new UserNotFoundException("User not found!");
        }
        log.info("Inserted!");
        return ResponseEntity.ok(toppingService.insertTaste(toppingDTO, myUserDetails.getUserDTO()));
    }
}
