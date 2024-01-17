package com.example.lunchmateback.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.lunchmateback.dtos.CategoryDto;
import com.example.lunchmateback.models.Category;
import com.example.lunchmateback.repositories.CategoryRepository;

@RestController
@RequestMapping(value = "/api/categories")
public class CategoriesController {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> get() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        List<CategoryDto> dtos = new ArrayList<>();
        for(Category c : categories) {
            CategoryDto dto = new CategoryDto();
            dto.setId(c.getId());
            dto.setName(c.getName());
            dto.setImage(c.getImage());
            dtos.add(dto);
        }

        return ResponseEntity.ok().body(dtos);
    }
}
