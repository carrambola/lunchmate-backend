package com.example.lunchmateback.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.lunchmateback.models.Recipe;
import com.example.lunchmateback.models.RecipeIngridient;
import com.example.lunchmateback.models.RecipeIngridientId;

@Repository
public interface RecipeIngridientRepository extends CrudRepository<RecipeIngridient, RecipeIngridientId> {
    List<RecipeIngridient> findByRecipe(Recipe recipe);
    Long deleteByRecipe(Recipe recipe);
}
