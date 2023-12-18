package com.example.lunchmateback.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.lunchmateback.models.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long>{
    
}
