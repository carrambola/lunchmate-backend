package com.example.lunchmateback.dtos;

import com.example.lunchmateback.models.Comment;
import com.example.lunchmateback.models.Recipe;
import com.example.lunchmateback.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private Boolean locked;
    private Boolean enable;
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String birthDate;

}
