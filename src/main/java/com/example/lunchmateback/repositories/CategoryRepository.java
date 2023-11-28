package com.example.lunchmateback.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.lunchmateback.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{
    
}
