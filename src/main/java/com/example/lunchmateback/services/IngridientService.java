package com.example.lunchmateback.services;

import java.util.List;
import com.example.lunchmateback.dtos.IngridientDto;

public interface IngridientService {
    List<IngridientDto> getIngridients();
    
}
