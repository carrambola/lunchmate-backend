package com.example.lunchmateback.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.lunchmateback.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{
    
}
