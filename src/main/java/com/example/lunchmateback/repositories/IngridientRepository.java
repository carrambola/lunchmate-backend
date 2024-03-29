package com.example.lunchmateback.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.lunchmateback.models.Ingridient;

@Repository
public interface IngridientRepository extends CrudRepository<Ingridient, Long>{
    List<Ingridient> findAllByOrderByNameAsc();
}
