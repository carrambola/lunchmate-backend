package com.example.lunchmateback.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.lunchmateback.utils.TestowaKlasa;

@RestController
@RequestMapping(value = "/api/test")
public class TestApiController {
    
    @GetMapping(value = "testOli")
    public String test() {
        return "Ola tu była";
    }

    @RequestMapping(value = "xyz", method = RequestMethod.POST)
    @ResponseBody
    public TestowaKlasa metoda() {
        return new TestowaKlasa(1, 2);
    }
}

