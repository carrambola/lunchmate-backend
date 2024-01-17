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
public class CommentUpdateRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private Long commentId;

}
