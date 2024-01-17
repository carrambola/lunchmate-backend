package com.example.lunchmateback.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lunchmateback.services.IngridientService;

@RestController

@RequestMapping(value="/api/ingridients")
public class IngridientController {

    @Autowired
    IngridientService ingridientService;

    @GetMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(ingridientService.getIngridients());
    }
}
