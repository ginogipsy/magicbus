package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.customexception.notfound.UserNotFoundException;
import com.ginogipsy.magicbus.dto.FriedDTO;
import com.ginogipsy.magicbus.service.FriedService;
import com.ginogipsy.magicbus.service.UserDetailsImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fried")
public class FriedController {

    private final FriedService friedService;

    public FriedController(FriedService friedService) {
        this.friedService = friedService;
    }

    @PutMapping("/insert")
    @ApiOperation(value = "Insert fried", notes = "Insert a fried")
    public ResponseEntity<FriedDTO> insertFried(@RequestBody FriedDTO friedDTO, @AuthenticationPrincipal UserDetailsImpl myUserDetails, BindingResult result){

        if(result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        if(myUserDetails == null){
            throw new UserNotFoundException("User not found!");
        }
        return ResponseEntity.ok(friedService.insertFried(friedDTO, myUserDetails.getUserDTO()));
    }
}
