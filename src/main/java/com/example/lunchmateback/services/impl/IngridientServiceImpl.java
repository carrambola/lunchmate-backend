package com.example.lunchmateback.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lunchmateback.dtos.IngridientDto;
import com.example.lunchmateback.models.Ingridient;
import com.example.lunchmateback.repositories.IngridientRepository;
import com.example.lunchmateback.services.IngridientService;

@Service
public class IngridientServiceImpl implements IngridientService {

    @Autowired
    private IngridientRepository ingridientRepository;

    @Override
    public List<IngridientDto> getIngridients() {
        List<Ingridient> ingridients = ingridientRepository.findAllByOrderByNameAsc();
        List<IngridientDto> dtos = new ArrayList<>();
        for(Ingridient i : ingridients) {
            dtos.add(convert(i));
        }
        return dtos;
    }

    private IngridientDto convert(Ingridient ingridient) {
        IngridientDto dto = new IngridientDto();
        dto.setId(ingridient.getId());
        dto.setName(ingridient.getName());
        return dto;
    }
    
}
