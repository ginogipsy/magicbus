package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.component.EmailService;
import com.ginogipsy.magicbus.controller.payload.request.LoginRequest;
import com.ginogipsy.magicbus.controller.payload.request.SignupRequest;
import com.ginogipsy.magicbus.controller.payload.request.TokenRefreshRequest;
import com.ginogipsy.magicbus.controller.payload.request.UpdatePasswordRequest;
import com.ginogipsy.magicbus.controller.payload.response.JwtResponse;
import com.ginogipsy.magicbus.controller.payload.response.MessageResponse;
import com.ginogipsy.magicbus.controller.payload.response.TokenRefreshResponse;
import com.ginogipsy.magicbus.dto.RefreshTokenDTO;
import com.ginogipsy.magicbus.dto.RoleDTO;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.exceptionhandler.AttributeForErrorEnum;
import com.ginogipsy.magicbus.exceptionhandler.MagicbusException;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import com.ginogipsy.magicbus.security.jwt.JwtUtils;
import com.ginogipsy.magicbus.service.RefreshTokenService;
import com.ginogipsy.magicbus.service.UpdatePassword;
import com.ginogipsy.magicbus.service.UserDetailsImpl;
import com.ginogipsy.magicbus.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.ginogipsy.magicbus.exceptionhandler.BeErrorCodeEnum.*;
/**
 * @author ginogipsy
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final MapperFactory mapperFactory;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;
    private final UpdatePassword updatePassword;
    private final EmailService emailService;



    public AuthController(AuthenticationManager authenticationManager,
                          MapperFactory mapperFactory,
                          JwtUtils jwtUtils,
                          UserService userService,
                          RefreshTokenService refreshTokenService,
                          PasswordEncoder passwordEncoder,
                          UpdatePassword updatePassword, EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.mapperFactory = mapperFactory;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
        this.passwordEncoder = passwordEncoder;
        this.updatePassword = updatePassword;
        this.emailService = emailService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);


        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        RefreshTokenDTO refreshTokenDTO = refreshTokenService.createRefreshToken(userDetails.id());

        return ResponseEntity.ok(new JwtResponse(jwt, refreshTokenDTO.getToken(), userDetails.id(),
                userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshTokenDTO::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new MagicbusException(REFRESH_TOKEN_NOT_FOUND, String.format("Failed for [%s]: %s", requestRefreshToken, "Refresh token is not in database!")));

    }

    @PostMapping("/signUp")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws MessagingException, IOException {
        if (Boolean.TRUE.equals(mapperFactory.getUserMapper().existsByUsername(signUpRequest.getUsername()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (Boolean.TRUE.equals(mapperFactory.getUserMapper().existsByEmail(signUpRequest.getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(signUpRequest.getUsername());
        userDTO.setEmail(signUpRequest.getEmail());
        userDTO.setPassword(passwordEncoder.encode(signUpRequest.getPassword().trim()));
        userDTO.setCellNumber(signUpRequest.getCellNumber());
        Optional.ofNullable(signUpRequest.getName()).ifPresent(userDTO::setName);
        Optional.ofNullable(signUpRequest.getSurname()).ifPresent(userDTO::setSurname);
        Optional.ofNullable(signUpRequest.getAddress()).ifPresent(userDTO::setAddress);
        Optional.ofNullable(signUpRequest.getHouseNumber()).ifPresent(userDTO::setHouseNumber);
        Optional.ofNullable(signUpRequest.getCity()).ifPresent(userDTO::setCity);
        Optional.ofNullable(signUpRequest.getPostalCode()).ifPresent(userDTO::setPostalCode);
        Optional.ofNullable(signUpRequest.getFiscalCode()).ifPresent(userDTO::setFiscalCode);

        Set<RoleDTO> roles = new HashSet<>();
        Set<String> strRoles = Optional.ofNullable(signUpRequest.getRoles()).orElse(new HashSet<>());
        if(strRoles.isEmpty()){
            mapperFactory.getRoleMapper().findByProfile("USER")
                    .map(roles::add)
                    .orElseThrow(() -> mapRoleException("USER"));
        }else{
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin", "ADMIN" -> mapperFactory.getRoleMapper().findByProfile("ADMIN")
                            .map(roles::add)
                            .orElseThrow(() -> mapRoleException("ADMIN"));
                    case "editor", "EDITOR" ->
                           mapperFactory.getRoleMapper().findByProfile("EDITOR")
                                    .map(roles::add)
                                    .orElseThrow(() -> mapRoleException("EDITOR"));
                    case "user", "USER" -> mapperFactory.getRoleMapper().findByProfile("USER")
                            .map(roles::add)
                            .orElseThrow(() -> mapRoleException("USER"));
                    case "mezz", "MEZZ" -> mapperFactory.getRoleMapper().findByProfile("MEZZ")
                            .map(roles::add)
                            .orElseThrow(() -> mapRoleException("MEZZ"));
                }
            });
        }

        userDTO.setRoles(roles);
        userService.signUpUser(userDTO);
        //emailService.sendRegistrationMail(userDTO);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<UserDTO> updatePassword(@RequestBody @Validated UpdatePasswordRequest updatePasswordRequest, BindingResult bindingResult){
        if(!bindingResult.hasErrors()) {
            UserDTO user = updatePassword.updatePassword(updatePasswordRequest.getEmail(), updatePasswordRequest.getOldPassword(), updatePasswordRequest.getNewPassword());
            return (user != null) ? ResponseEntity.ok().body(user) : ResponseEntity.badRequest().build();
        }else
            throw new MagicbusException(DATA_NOT_CORRECT);
    }

    private MagicbusException mapRoleException(final String role) {
        Map<String,String> attributeList = new HashMap<>();
        attributeList.put(AttributeForErrorEnum.ATTRIBUTE_1.getDescription(), role);
        return new MagicbusException(ROLE_NOT_FOUND, attributeList);
    }

}