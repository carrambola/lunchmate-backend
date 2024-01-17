package com.example.lunchmateback.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.lunchmateback.models.Recipe;

@Repository
public interface RecipesRepository extends CrudRepository<Recipe, Long> {
    // List<Recipe> findOrderByName();
    List<Recipe> findTop10ByOrderByLikesDesc();
    List<Recipe> findTop10ByOrderByCreatedAtDesc();
    List<Recipe> findTop10ByOrderByDifficultyAsc();
}
