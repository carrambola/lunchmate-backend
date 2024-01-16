package com.example.lunchmateback.controllers;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.lunchmateback.models.ConfirmationToken;
import com.example.lunchmateback.services.AuthApiService;
import com.example.lunchmateback.services.ConfirmationTokenService;
import com.example.lunchmateback.services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.example.lunchmateback.dtos.JwtResponse;
import com.example.lunchmateback.dtos.LoginRequest;
import com.example.lunchmateback.dtos.MessageResponse;
import com.example.lunchmateback.dtos.SignupRequest;
import com.example.lunchmateback.jwt.JwtUtils;
import com.example.lunchmateback.models.ERole;
import com.example.lunchmateback.models.Role;
import com.example.lunchmateback.models.User;
import com.example.lunchmateback.repositories.RoleRepository;
import com.example.lunchmateback.repositories.UserRepository;
import com.example.lunchmateback.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthApiController {

    private final ConfirmationTokenService confirmationTokenService;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthApiService authApiService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authApiService.authenticateUser(loginRequest);
    }

    @GetMapping(path = "/confirm")
    public ResponseEntity<?> confirmToken(@RequestParam("token") String token) {
        return authApiService.confirmToken(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authApiService.registerUser(signUpRequest);
    }

}
