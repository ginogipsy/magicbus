package com.ginogipsy.magicbus.controller;


import com.ginogipsy.magicbus.customexception.controller.DataNotCorrectException;
import com.ginogipsy.magicbus.customexception.user.EmailIsPresentException;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.controller.payload.request.usercontroller.AddAddressRequest;
import com.ginogipsy.magicbus.controller.payload.request.usercontroller.UpdateUserRequest;
import com.ginogipsy.magicbus.service.UserDetailsImpl;
import com.ginogipsy.magicbus.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UpdateUserRequest updateUserRequest, @AuthenticationPrincipal UserDetailsImpl myUserDetails){
        UserDTO userDTO = createUserDTO(updateUserRequest);
        UserDTO user = userService.updateUser(myUserDetails.getUserDTO(), userDTO);
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/addFiscalCode")
    public ResponseEntity<UserDTO> addFiscalCode(@RequestParam String fiscalCode, @AuthenticationPrincipal UserDetailsImpl myUserDetails){
        UserDTO user = userService.addFiscalCode(myUserDetails.getUserDTO(), fiscalCode);
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/addAddress")
    public ResponseEntity<UserDTO> addAddress(@RequestBody @Validated AddAddressRequest addAddressRequest, @AuthenticationPrincipal UserDetailsImpl myUserDetails, BindingResult bindingResult){
        if(!bindingResult.hasErrors()) {
            UserDTO user = userService.addAddress(myUserDetails.getUserDTO(), addAddressRequest.getAddress(), addAddressRequest.getHouseNumber(), addAddressRequest.getCity(), addAddressRequest.getPostalCode());
            return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
        }else
            throw new DataNotCorrectException("Indirizzo inserito non corretto");
    }

    @PutMapping("/addNameAndSurname")
    public ResponseEntity<UserDTO> addNameAndSurname(@RequestParam String name, @RequestParam String surname, @AuthenticationPrincipal UserDetailsImpl myUserDetails){
        UserDTO user = userService.addNameAndSurname(myUserDetails.getUserDTO(), name, surname);
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/updateEmail")
    @ExceptionHandler(value = EmailIsPresentException.class)
    public ResponseEntity<UserDTO> updateEmail(@RequestParam String newEmail, @AuthenticationPrincipal UserDetailsImpl myUserDetails){
            UserDTO user = userService.updateEmail(myUserDetails.getUserDTO(), newEmail);
            return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/updateUsername")
    public ResponseEntity<UserDTO> updateUsername(@RequestParam String username, @AuthenticationPrincipal UserDetailsImpl myUserDetails){
        UserDTO user = userService.updateUsername(myUserDetails.getUserDTO(), username);
        return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
    }

    @PutMapping("/updateCellNumber")
    public ResponseEntity<UserDTO> updateCellNumber(@RequestParam String newCellNumber, @AuthenticationPrincipal UserDetailsImpl myUserDetails){
        UserDTO user = userService.updateCellNumber(myUserDetails.getUserDTO(), newCellNumber);
        return ResponseEntity.ok().body(user);
    }

    private UserDTO createUserDTO(UpdateUserRequest updateUserRequest){
        UserDTO userDTO = new UserDTO();
        if(updateUserRequest.getEmail() != null) userDTO.setEmail(updateUserRequest.getEmail());
        if(updateUserRequest.getUsername() != null) userDTO.setUsername(updateUserRequest.getUsername());
        if(updateUserRequest.getName() != null) userDTO.setName(updateUserRequest.getName());
        if(updateUserRequest.getSurname() != null) userDTO.setSurname(updateUserRequest.getSurname());
        if(updateUserRequest.getCellNumber() != null) userDTO.setCellNumber(updateUserRequest.getCellNumber());
        if(updateUserRequest.getAddress() != null) userDTO.setAddress(updateUserRequest.getAddress());
        if(updateUserRequest.getHouseNumber() != null) userDTO.setHouseNumber(updateUserRequest.getHouseNumber());
        if(updateUserRequest.getCity() != null) userDTO.setCity(updateUserRequest.getCity());
        if(updateUserRequest.getPostalCode() != null) userDTO.setPostalCode(updateUserRequest.getPostalCode());
        if(updateUserRequest.getFiscalCode() != null) userDTO.setFiscalCode(updateUserRequest.getFiscalCode());
        return userDTO;
    }
}
