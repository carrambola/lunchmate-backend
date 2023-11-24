package com.example.lunchmateback.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.lunchmateback.models.Ingridient;

public interface IngridientRepository extends CrudRepository<Ingridient, Long>{
    
}
