package com.example.lunchmateback.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    @NotBlank
    private Long author;
    @NotBlank
    private Long recipe;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
}
