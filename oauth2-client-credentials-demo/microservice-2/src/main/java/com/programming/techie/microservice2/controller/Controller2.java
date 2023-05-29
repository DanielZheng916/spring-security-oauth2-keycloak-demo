package com.programming.techie.microservice2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller2 {

    @GetMapping("/microservice2/home")
    public String helloRestTemplate() {
        return "Hello 2";
    }
}
