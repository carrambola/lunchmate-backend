package com.example.lunchmateback.services;

import java.util.List;

import com.example.lunchmateback.dtos.RecipeDto;

public interface RecipeService {
    RecipeDto getRecipe(Long id);

    List<RecipeDto> getRecipes();

    RecipeDto saveRecipe(RecipeDto dto);

    Boolean addLikeToRecipe(Long recipeId);

    List<RecipeDto> getMostLikedRecipes();

    RecipeDto update(RecipeDto entity, Long id);

    Boolean delete(Long id);

    Object getMostRecentRecipes();

    Object getMostEasyRecipes();
}
