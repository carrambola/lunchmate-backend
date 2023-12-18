package com.example.lunchmateback.controllers;

import com.example.lunchmateback.dtos.RecipeDto;
import com.example.lunchmateback.models.Category;
import com.example.lunchmateback.models.Recipe;
import com.example.lunchmateback.models.User;
import com.example.lunchmateback.repositories.CategoryRepository;
import com.example.lunchmateback.repositories.RecipesRepository;
import com.example.lunchmateback.repositories.UserRepository;
import com.example.lunchmateback.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(Long id) {

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> oUser = repository.findById(userDetails.getId());
        if(oUser.isEmpty()) { return ResponseEntity.badRequest().build(); }
        return ResponseEntity.ok().body(oUser.get());
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveRecipe(@RequestBody RecipeDto dto) {

        return ResponseEntity.status(405).build();

    }

}
