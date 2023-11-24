package com.example.lunchmateback.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.lunchmateback.models.Recipe;

public interface RecipieRepository extends CrudRepository<Recipe, Long> {
    
}
