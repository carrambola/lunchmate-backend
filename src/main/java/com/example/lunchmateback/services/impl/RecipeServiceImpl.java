package com.example.lunchmateback.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipesRepository recipesRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private IngridientRepository ingridientRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public RecipeDto getRecipe(Long id) {
        Optional<Recipe> oRecipe = recipesRepository.findById(id);
        if(!oRecipe.isPresent()) {
            return null;
        }

        RecipeDto x = new RecipeDto();
            x.setCategoryId(oRecipe.get().getCategory().getId());
            x.setDescription(oRecipe.get().getDescription());
            x.setId(oRecipe.get().getId());
            x.setName(oRecipe.get().getName());
            x.setUserId(oRecipe.get().getUser().getId());
            x.setAmountTimeToPrepare(oRecipe.get().getAmountTimeToPrepare());
            x.setPhotoUrl(oRecipe.get().getPhotoUrl());
            x.setDifficulty(oRecipe.get().getDifficulty());

        Hibernate.initialize(oRecipe.get().getIngridients());

        for(RecipeIngridient ri : oRecipe.get().getIngridients()) {
            RecipeIngridientDto riDto = new RecipeIngridientDto();
            riDto.setAmount(ri.getAmount());
            riDto.setIngridientId(ri.getId().getIngridientId());
            riDto.setScale(ri.getScale());

            x.getIngridients().add(riDto);
        }

        Hibernate.initialize(oRecipe.get().getLikes());

        x.setLikesCount(oRecipe.get().getLikes());

        return x;
    }

    @Override
    // @Transactional
    public List<RecipeDto> getRecipes() {
        List<Recipe>  recipes = (List<Recipe>) recipesRepository.findAll(); //rzutuje iterable na liste bo inaczej mam edncode error
        List<RecipeDto> recipeDtos = new ArrayList<>();
        for (Recipe r : recipes){
            // 

            RecipeDto x = new RecipeDto();
            x.setCategoryId(r.getCategory().getId());
            x.setDescription(r.getDescription());
            x.setId(r.getId());
            x.setName(r.getName());
            x.setUserId(r.getUser().getId());
            x.setAmountTimeToPrepare(r.getAmountTimeToPrepare());
            x.setPhotoUrl(r.getPhotoUrl());
            x.setDifficulty(r.getDifficulty());
            x.setLikesCount(r.getLikes());

            

            recipeDtos.add(x);
        }

        return recipeDtos;
    }

    @Override
    @Transactional
    public RecipeDto saveRecipe(RecipeDto dto) {
        Recipe recipe = new Recipe();
        recipe.setDescription(dto.getDescription());
        recipe.setName(dto.getName());
        recipe.setCreatedAt(new Date());
        
        // kategoria
        Optional<Category> oCategory = categoryRepository.findById(dto.getCategoryId());
        if(!oCategory.isPresent()) {
            return null;
        }

        recipe.setCategory(oCategory.get());

        // author - uzyskanie uzytkownika
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> oUser = userRepository.findById(userDetails.getId());
        if(!oUser.isPresent()) { 
            return null; 
        }
        recipe.setUser(oUser.get());
        
        // dodaje skladniki
        List<RecipeIngridient> ingridients = new ArrayList<>();
        for(RecipeIngridientDto riDto : dto.getIngridients()) {
            Optional<Ingridient> oIngridient = ingridientRepository.findById(riDto.getIngridientId());
            if(oIngridient.isPresent()) {
                RecipeIngridient ri = new RecipeIngridient();
                ri.setAmount(riDto.getAmount());
                ri.setIngridient(oIngridient.get());
                ri.setScale(riDto.getScale());
                ingridients.add(ri);
            }
        }
        recipe.setIngridients(ingridients);

        // url do zdjecia
        recipe.setPhotoUrl(dto.getPhotoUrl());

        recipe.setAmountTimeToPrepare(dto.getAmountTimeToPrepare());

        recipe.setDifficulty(dto.getDifficulty());

        recipe = recipesRepository.save(recipe);

        // zwrotna odpowiedz
        return getRecipe(recipe.getId());
    }

    @Override
    public Boolean addLikeToRecipe(Long recipeId) {
        Optional<Recipe> oReOptional = recipesRepository.findById(recipeId);
        if(!oReOptional.isPresent()) {
            return false;
        }

        Recipe r = oReOptional.get();
        r.setLikes(r.getLikes() + 1);

        recipesRepository.save(r);
        return true;
    }

    
}
