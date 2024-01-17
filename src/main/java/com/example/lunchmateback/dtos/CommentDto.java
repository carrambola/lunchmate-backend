package com.example.lunchmateback.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private Long id;
    private String title;
    private String description;
    private String authorDescription;
    private String authorId;
}
