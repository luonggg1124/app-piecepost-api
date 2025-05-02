package com.piecepost.user.https.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TestController {
    @Value("${server.port}")
    public String port;

    @GetMapping("/")
    public ResponseEntity<Map<String,String>> index(){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","User service is running at port "+port));
    }
}
