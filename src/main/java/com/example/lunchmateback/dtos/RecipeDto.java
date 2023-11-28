package com.example.lunchmateback.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeDto {
    private String name;
    private String description;
    private Long categoryId;
    private Long id; 
    private Long userId;
}
