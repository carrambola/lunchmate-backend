package com.example.lunchmateback.controllers;

import com.example.lunchmateback.dtos.MessageResponse;
import com.example.lunchmateback.dtos.UserDto;
import com.example.lunchmateback.models.User;
import com.example.lunchmateback.repositories.UserRepository;
import com.example.lunchmateback.services.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user")
@CrossOrigin
@AllArgsConstructor
public class UserController {
    private final UserDetailsServiceImpl userService;

    @GetMapping(path = "/getUserById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserById(@RequestParam("id") Long id) {
        return userService.getUserById(id);
    }

}
