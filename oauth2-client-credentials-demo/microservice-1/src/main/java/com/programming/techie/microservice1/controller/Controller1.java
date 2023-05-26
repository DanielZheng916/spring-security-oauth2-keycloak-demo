package com.programming.techie.microservice1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller1 {

    @GetMapping("/microservice1/home")
    public String helloRestTemplate() {
        return "Hello";
    }
}
