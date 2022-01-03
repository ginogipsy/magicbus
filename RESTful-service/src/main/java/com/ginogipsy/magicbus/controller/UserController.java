package com.ginogipsy.magicbus.controller;


import com.ginogipsy.magicbus.customexception.controller.DataNotCorrectException;
import com.ginogipsy.magicbus.customexception.user.EmailIsPresentException;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.controller.payload.request.usercontroller.AddAddressRequest;
import com.ginogipsy.magicbus.controller.payload.request.usercontroller.UpdateUserRequest;
import com.ginogipsy.magicbus.service.UserDetailsImpl;
import com.ginogipsy.magicbus.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findByEmail")
    @ApiOperation(value = "get user", notes = "get user by email")
    public ResponseEntity<UserDTO> findByEmail(@RequestParam String email){
        UserDTO user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/updateUser")
    @ApiOperation(value = "Update user", notes = "Update user")
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UpdateUserRequest updateUserRequest, @AuthenticationPrincipal UserDetailsImpl myUserDetails){
        final UserDTO userDTO = createUserDTO(updateUserRequest, myUserDetails.getUserDTO());
        final UserDTO user = userService.updateUser(myUserDetails.getUserDTO(), userDTO);
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/addFiscalCode")
    @ApiOperation(value = "Update user", notes = "Update fiscalCode of user")
    public ResponseEntity<UserDTO> addFiscalCode(@RequestBody String fiscalCode, @AuthenticationPrincipal UserDetailsImpl myUserDetails, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        UserDTO user = userService.addFiscalCode(myUserDetails.getUserDTO(), fiscalCode);
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/addAddress")
    @ApiOperation(value = "Update user", notes = "Update address of user")
    public ResponseEntity<UserDTO> addAddress(@RequestBody @Validated AddAddressRequest addAddressRequest, @AuthenticationPrincipal UserDetailsImpl myUserDetails, BindingResult bindingResult){
        if(!bindingResult.hasErrors()) {
            UserDTO user = userService.addAddress(myUserDetails.getUserDTO(), addAddressRequest.getAddress(), addAddressRequest.getHouseNumber(), addAddressRequest.getCity(), addAddressRequest.getPostalCode());
            return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
        }else
            throw new DataNotCorrectException("Indirizzo inserito non corretto");
    }

    @PutMapping("/addNameAndSurname")
    @ApiOperation(value = "Update user", notes = "Update name and surname of user")
    public ResponseEntity<UserDTO> addNameAndSurname(@RequestParam String name, @RequestParam String surname, @AuthenticationPrincipal UserDetailsImpl myUserDetails){
        UserDTO user = userService.addNameAndSurname(myUserDetails.getUserDTO(), name, surname);
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/updateEmail")
    @ApiOperation(value = "Update user", notes = "Update email of user")
    @ExceptionHandler(value = EmailIsPresentException.class)
    public ResponseEntity<UserDTO> updateEmail(@RequestParam String newEmail, @AuthenticationPrincipal UserDetailsImpl myUserDetails){
            UserDTO user = userService.updateEmail(myUserDetails.getUserDTO(), newEmail);
            return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/updateUsername")
    @ApiOperation(value = "Update user", notes = "Update username of user")
    public ResponseEntity<UserDTO> updateUsername(@RequestParam String username, @AuthenticationPrincipal UserDetailsImpl myUserDetails){
        UserDTO user = userService.updateUsername(myUserDetails.getUserDTO(), username);
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/updateCellNumber")
    @ApiOperation(value = "Update user", notes = "Update cell number of user")
    public ResponseEntity<UserDTO> updateCellNumber(@RequestParam String newCellNumber, @AuthenticationPrincipal UserDetailsImpl myUserDetails){
        UserDTO user = userService.updateCellNumber(myUserDetails.getUserDTO(), newCellNumber);
        return ResponseEntity.ok().body(user);
    }

    private UserDTO createUserDTO(final UpdateUserRequest updateUserRequest, final UserDTO userDTO){
        final UserDTO updatedUserDTO = new UserDTO();
        Optional.ofNullable(updateUserRequest.getEmail())
                .ifPresentOrElse(updatedUserDTO::setEmail,() -> updatedUserDTO.setEmail(userDTO.getEmail()));
        Optional.ofNullable(updateUserRequest.getUsername())
                .ifPresentOrElse(updatedUserDTO::setUsername, () -> updatedUserDTO.setUsername(userDTO.getUsername()));
        Optional.ofNullable(updateUserRequest.getName()).ifPresent(updatedUserDTO::setName);
        Optional.ofNullable(updateUserRequest.getSurname()).ifPresent(updatedUserDTO::setSurname);
        Optional.ofNullable(updateUserRequest.getCellNumber()).ifPresent(updatedUserDTO::setCellNumber);
        Optional.ofNullable(updateUserRequest.getAddress()).ifPresent(updatedUserDTO::setAddress);
        Optional.ofNullable(updateUserRequest.getHouseNumber()).ifPresent(updatedUserDTO::setHouseNumber);
        Optional.ofNullable(updateUserRequest.getCity()).ifPresent(updatedUserDTO::setCity);
        Optional.ofNullable(updateUserRequest.getPostalCode()).ifPresent(updatedUserDTO::setPostalCode);
        Optional.ofNullable(updateUserRequest.getFiscalCode()).ifPresent(updatedUserDTO::setFiscalCode);
        return updatedUserDTO;
    }
}
