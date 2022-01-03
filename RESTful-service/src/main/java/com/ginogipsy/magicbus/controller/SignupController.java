package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.customexception.user.CellPhoneIsPresentException;
import com.ginogipsy.magicbus.customexception.user.CellPhoneNotCorrectException;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signupUser")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    @ApiOperation(value = "Insert user", notes = "Insert a new user")
    public ResponseEntity<UserDTO> signUp(@RequestBody UserDTO userDTO, BindingResult result) throws CellPhoneNotCorrectException, CellPhoneIsPresentException {

        if(!result.hasErrors()){
            UserDTO user =  userService.signUpUser(userDTO);
            return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
        }

        return ResponseEntity.badRequest().build();

    }
}
