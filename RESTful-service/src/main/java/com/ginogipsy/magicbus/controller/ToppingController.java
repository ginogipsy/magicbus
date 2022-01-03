package com.ginogipsy.magicbus.controller;


import com.ginogipsy.magicbus.customexception.notfound.UserNotFoundException;
import com.ginogipsy.magicbus.dto.ToppingDTO;
import com.ginogipsy.magicbus.service.TasteService;
import com.ginogipsy.magicbus.service.UserDetailsImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/topping")
public class ToppingController {

    private final TasteService tasteService;

    public ToppingController(TasteService tasteService) {
        this.tasteService = tasteService;
    }

    @PutMapping("/insert")
    @ApiOperation(value = "Insert topping", notes = "Insert a new topping")
    public ResponseEntity<ToppingDTO> insertTaste(@RequestBody @Valid final ToppingDTO toppingDTO, final @AuthenticationPrincipal UserDetailsImpl myUserDetails){

        if(myUserDetails == null){
            throw new UserNotFoundException("User not found!");
        }
        return ResponseEntity.ok(tasteService.insertTaste(toppingDTO, myUserDetails.getUserDTO()));
    }
}
