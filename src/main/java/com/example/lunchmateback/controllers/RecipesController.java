package com.example.lunchmateback.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.lunchmateback.dtos.RecipeDto;
import com.example.lunchmateback.models.Category;
import com.example.lunchmateback.models.Recipe;
import com.example.lunchmateback.models.User;
import com.example.lunchmateback.repositories.CategoryRepository;
import com.example.lunchmateback.repositories.RecipesRepository;
import com.example.lunchmateback.repositories.UserRepository;
import com.example.lunchmateback.services.UserDetailsImpl;

@RestController
@RequestMapping(value = "/api/recipes")
@CrossOrigin
public class RecipesController {

    @Autowired
    private RecipesRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> getRecipes() {
        List<Recipe> recipes = (List<Recipe>) repository.findAll(); //rzutuje iterable na liste bo inaczej mam edncode error
        List<RecipeDto> recipeDtos = new ArrayList<>();
        for (Recipe r : recipes) {
            RecipeDto x = new RecipeDto();
            x.setCategoryId(r.getCategory().getId());
            x.setDescription(r.getDescription());
            x.setId(r.getId());
            x.setName(r.getName());
            x.setUserId(r.getUser().getId());
            recipeDtos.add(x);
        }
        return ResponseEntity.ok().body(recipeDtos);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRecipe(Long id) {
        Optional<Recipe> oRecipe = repository.findById(id);
        if (!oRecipe.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(oRecipe.get());
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveRecipe(@RequestBody RecipeDto dto) {

        // normalnie trzeba to pisac w Service
        // tu jest brzydko, ale prosto
        Recipe recipe = new Recipe();
        recipe.setDescription(dto.getDescription());
        recipe.setName(dto.getName());
        recipe.setCreatedAt(new Date());
        Optional<Category> oCategory = categoryRepository.findById(dto.getCategoryId());
        if (!oCategory.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        recipe.setCategory(oCategory.get());
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> oUser = userRepository.findById(userDetails.getId());
        if (!oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        recipe.setUser(oUser.get());

        recipe = repository.save(recipe);

        RecipeDto rDto = new RecipeDto();

        rDto.setId(recipe.getId());
        rDto.setUserId(recipe.getUser().getId());

        if (recipe != null && recipe.getId() != null) {
            return ResponseEntity.ok().body(rDto);
        }

        return ResponseEntity.status(405).build();

    }

}
