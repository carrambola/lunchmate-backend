package com.example.lunchmateback.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // zawsze pusty konstruktor w klasach DTO
public class LoginRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
