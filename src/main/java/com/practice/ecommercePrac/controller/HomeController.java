package com.practice.ecommercePrac.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public String homeControllerHandler(){
        return "Welcome to ecommerce multi vendor system";
    }
}
