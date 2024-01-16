package com.example.lunchmateback.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class IngridientDto {
    private Long id;
    private String name;
    private Integer calories;
}
