package com.example.lunchmateback.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RecipeIngridientDto {
    private Long ingridientId;
    private Long recipeId;
    private Integer amount;
    private String scale;
}
