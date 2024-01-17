package com.example.lunchmateback.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.lunchmateback.dtos.RecipeDto;
import com.example.lunchmateback.dtos.RecipeIngridientDto;
import com.example.lunchmateback.models.Category;
import com.example.lunchmateback.models.Ingridient;
import com.example.lunchmateback.models.Recipe;
import com.example.lunchmateback.models.RecipeIngridient;
import com.example.lunchmateback.models.User;
import com.example.lunchmateback.repositories.CategoryRepository;
import com.example.lunchmateback.repositories.IngridientRepository;
import com.example.lunchmateback.repositories.RecipesRepository;
import com.example.lunchmateback.repositories.UserRepository;
import com.example.lunchmateback.services.RecipeService;
import com.example.lunchmateback.services.UserDetailsImpl;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping(value="/api/recipes")
@CrossOrigin
public class RecipesController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> getRecipes() {
        return ResponseEntity.ok().body(recipeService.getRecipes());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRecipe(@PathVariable(value = "id") Long id) {
        RecipeDto dto = recipeService.getRecipe(id);
        if(dto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(dto);
    }
    
    @GetMapping(value="/mostLiked", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMostLikedRecipes() {
        return ResponseEntity.ok().body(recipeService.getMostLikedRecipes());
    }

    @GetMapping(value="/mostRecent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMostRecentRecipes() {
        return ResponseEntity.ok().body(recipeService.getMostRecentRecipes());
    }

    @GetMapping(value="/mostEasy", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMostEasyRecipes() {
        return ResponseEntity.ok().body(recipeService.getMostEasyRecipes());
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveRecipe(@RequestBody RecipeDto dto) {

        RecipeDto rDto = recipeService.saveRecipe(dto);

        if(rDto != null)  {
            return ResponseEntity.ok().body(rDto);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable Long id, @RequestBody RecipeDto entity) {
        RecipeDto dto = recipeService.update(entity, id);
        if(dto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteRecipe(@PathVariable Long id) {
        Boolean b = recipeService.delete(id);
        return ResponseEntity.ok().body(b);
    }

    @GetMapping(value = "/addLike/{recipeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addLikeToRecipe(@PathParam(value = "recipeId") Long recipeId) {
        Boolean resp = recipeService.addLikeToRecipe(recipeId);

        if(resp) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    
}
