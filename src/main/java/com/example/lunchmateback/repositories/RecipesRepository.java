package com.example.lunchmateback.repositories;

import java.util.List;
import java.util.Optional;

import com.example.lunchmateback.models.ERole;
import com.example.lunchmateback.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.lunchmateback.models.Recipe;

@Repository
public interface RecipesRepository extends CrudRepository<Recipe, Long> {
    Optional<Recipe> findByname(String name);
    @Override
    Optional<Recipe> findById(Long id);
}
