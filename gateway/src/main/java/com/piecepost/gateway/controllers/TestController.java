package com.piecepost.gateway.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TestController {
    @Value("${server.port}")
    private String port;
   
    @GetMapping("/")
    public Mono<ResponseEntity<Map<String, String>>> index() {
        Map<String, String> response = Map.of("message", "Gateway is running at port " + port);
        return Mono.just(ResponseEntity.ok(response));
    }
}
