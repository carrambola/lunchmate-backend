package com.example.lunchmateback.dtos;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeDto {
    private Long id; 
    private String name;
    private String description;
    
    private Long categoryId;
    private Long userId;

    private List<CommentDto> comments;
    private List<RecipeIngridientDto> ingridients = new ArrayList<>();

    private Long likes;

    private String image;
    private Integer time;

    @NotNull
    private String difficulty;
}
