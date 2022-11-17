package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/signupUser")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    @ApiOperation(value = "Insert user", notes = "Insert a new user")
    public ResponseEntity<UserDTO> signUp(@RequestBody UserDTO userDTO, BindingResult result) throws MagicbusException {
        log.info("Checking new user request..");
        if(!result.hasErrors()){
            log.info("Creating new user..");
            UserDTO user =  userService.signUpUser(userDTO);
            log.info("Created!");
            return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
        }
        log.info("Request body is not correct!");
        return ResponseEntity.badRequest().build();

    }
}
