package com.programming.techie.microservice1.controller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class Controller1 {

    private final RestTemplate restTemplate = new RestTemplateBuilder().build();
    private final WebClient webClient = WebClient.builder().build();

    @GetMapping("/microservice1/home")
    public String helloRestTemplate() {
        Jwt jwt = (Jwt)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer " + jwt.getTokenValue());

        ResponseEntity<String> stringResponseEntity = restTemplate.exchange("http://localhost:8082/microservice2/home",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class);

        System.out.println(stringResponseEntity.getBody());
        return "Hello with" + stringResponseEntity.getBody();
    }

    @GetMapping("/microservice1/home/webclient")
    public String helloWebClient() {
        Jwt jwt = (Jwt)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String response = webClient.get()
                .uri("http://localhost:8082/microservice2/home")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(jwt.getTokenValue()))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return "Hello with " + response;
    }
}
