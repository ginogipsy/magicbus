package com.ginogipsy.magicbus.controller;

import com.ginogipsy.magicbus.controller.payload.request.TokenRefreshRequest;
import com.ginogipsy.magicbus.controller.payload.response.TokenRefreshResponse;
import com.ginogipsy.magicbus.customexception.TokenRefreshException;
import com.ginogipsy.magicbus.customexception.notfound.RoleNotFoundException;
import com.ginogipsy.magicbus.domain.Profilo;
import com.ginogipsy.magicbus.dto.RefreshTokenDTO;
import com.ginogipsy.magicbus.dto.RoleDTO;
import com.ginogipsy.magicbus.dto.UserDTO;
import com.ginogipsy.magicbus.marshall.MapperFactory;
import com.ginogipsy.magicbus.controller.payload.request.LoginRequest;
import com.ginogipsy.magicbus.controller.payload.request.SignupRequest;
import com.ginogipsy.magicbus.controller.payload.response.JwtResponse;
import com.ginogipsy.magicbus.controller.payload.response.MessageResponse;
import com.ginogipsy.magicbus.security.jwt.JwtUtils;
import com.ginogipsy.magicbus.service.RefreshTokenService;
import com.ginogipsy.magicbus.service.UserDetailsImpl;
import com.ginogipsy.magicbus.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final MapperFactory mapperFactory;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;



    public AuthController(AuthenticationManager authenticationManager, MapperFactory mapperFactory, JwtUtils jwtUtils, UserService userService, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.mapperFactory = mapperFactory;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);


        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        RefreshTokenDTO refreshTokenDTO = refreshTokenService.createRefreshToken(userDetails.getId());

        return ResponseEntity.ok(new JwtResponse(jwt, refreshTokenDTO.getToken(), userDetails.getId(),
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
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (mapperFactory.getUserMapper().existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (mapperFactory.getUserMapper().existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(signUpRequest.getUsername());
        userDTO.setEmail(signUpRequest.getEmail());
        userDTO.setPassword(signUpRequest.getPassword());
        userDTO.setNumeroCellulare(signUpRequest.getNumeroCellulare());
        Optional.ofNullable(signUpRequest.getNome()).ifPresent(userDTO::setNome);
        Optional.ofNullable(signUpRequest.getCognome()).ifPresent(userDTO::setCognome);
        Optional.ofNullable(signUpRequest.getIndirizzo()).ifPresent(userDTO::setIndirizzo);
        Optional.ofNullable(signUpRequest.getCivico()).ifPresent(userDTO::setCivico);
        Optional.ofNullable(signUpRequest.getCitta()).ifPresent(userDTO::setCitta);
        Optional.ofNullable(signUpRequest.getCap()).ifPresent(userDTO::setCap);
        Optional.ofNullable(signUpRequest.getCodiceFiscale()).ifPresent(userDTO::setCodiceFiscale);

        Set<RoleDTO> roles = new HashSet<>();
        Set<String> strRoles = signUpRequest.getRoles();
        if(strRoles.isEmpty()){
            Optional.ofNullable(mapperFactory.getRoleMapper().findByProfilo(Profilo.USER))
                    .map(roles::add)
                    .orElseThrow(() -> new RoleNotFoundException("Error: Role USER is not found."));
        }else{
            strRoles.forEach(role -> {
            switch (role){
                case "admin":
                    Optional.ofNullable(mapperFactory.getRoleMapper().findByProfilo(Profilo.ADMIN))
                            .map(roles::add)
                            .orElseThrow(() -> new RoleNotFoundException("Error: Role ADMIN is not found."));
                    break;
                case "editor":
                    Optional.ofNullable(mapperFactory.getRoleMapper().findByProfilo(Profilo.EDITOR))
                            .map(roles::add)
                            .orElseThrow(() -> new RoleNotFoundException("Error: Role EDITOR is not found."));
                    break;
                default:
                case "user":
                    Optional.ofNullable(mapperFactory.getRoleMapper().findByProfilo(Profilo.USER))
                            .map(roles::add)
                            .orElseThrow(() -> new RoleNotFoundException("Error: Role USER is not found."));
                    break;
                case "mezz":
                    Optional.ofNullable(mapperFactory.getRoleMapper().findByProfilo(Profilo.MEZZ))
                            .map(roles::add)
                            .orElseThrow(() -> new RoleNotFoundException("Error: Role MEZZ is not found."));
                    break;

            }
        });
        }

        userDTO.setRoles(roles);
        userService.registrazioneUtente(userDTO);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
