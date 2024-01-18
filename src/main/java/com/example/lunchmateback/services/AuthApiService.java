package com.example.lunchmateback.services;

import com.example.lunchmateback.dtos.JwtResponse;
import com.example.lunchmateback.dtos.LoginRequest;
import com.example.lunchmateback.dtos.MessageResponse;
import com.example.lunchmateback.dtos.SignupRequest;
import com.example.lunchmateback.jwt.JwtUtils;
import com.example.lunchmateback.models.ConfirmationToken;
import com.example.lunchmateback.models.ERole;
import com.example.lunchmateback.models.Role;
import com.example.lunchmateback.models.User;
import com.example.lunchmateback.repositories.RoleRepository;
import com.example.lunchmateback.repositories.UserRepository;
import com.example.lunchmateback.utils.email.EmailSender;
import io.swagger.models.Response;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AuthApiService {
    private final ConfirmationTokenService confirmationTokenService;
    private final UserDetailsServiceImpl userDetailsService;

    private final EmailSender emailSender;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Transactional
    public ResponseEntity<?> confirmToken(String token) {

        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() -> new IllegalStateException("Nie znaleziono tokenu."));
        if (confirmationToken.getConfirmedAt() != null) {
            return ResponseEntity.badRequest().body("Konto już zostało zatwierdzone.");
        }
        if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Token już wygasł.");
        }
        confirmationTokenService.setConfirmedAt(token);
        userDetailsService.enableAppUser(confirmationToken.getUser().getEmail());
        return ResponseEntity.ok(new MessageResponse("Konto pomyślnie zatwierdzone"));
    }

    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        if (userRepository.findByUsername(loginRequest.getUsername()).isPresent()) {
            if (userRepository.findByUsername(loginRequest.getUsername()).get().getEnable()) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication);

                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                List<String> roles = userDetails.getAuthorities().stream()
                        .map(item -> item.getAuthority())
                        .collect(Collectors.toList());

                return ResponseEntity.ok(new JwtResponse(jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
            } else {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Konto nie potwierdzone. Sprawdź skrzynkę email po wiadomość weryfikacyjną."));
            }
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Nie znaleziono użytkownika."));
        }

    }

    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Nazwa użytkownika jest już zajęta."));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Adres email jest już zajęty."));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getName(),
                signUpRequest.getSurname(),
                signUpRequest.getBirthDate()
        );

        // Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();


        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono roli."));
        roles.add(userRole);


        user.setRoles(roles);
        userRepository.save(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        String link = "http://localhost:8080/api/auth/confirm?token=" + token;

        emailSender.send(signUpRequest.getEmail(), buildEmail(signUpRequest.getName(), link));
        return ResponseEntity.ok(new MessageResponse(token));
    }

    private String buildEmail(String name, String link) {
        System.out.println(link);
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_blank\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";
        content = content.replace("[[name]]", name);
        System.out.println(content);
        content = content.replace("[[URL]]", link);
        System.out.println(content);
        return content;
    }

}
