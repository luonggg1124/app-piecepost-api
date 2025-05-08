package com.piecepost.user.https.controllers;

import java.util.Map;

import com.piecepost.grpc.cache.CacheRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piecepost.grpc.cache.CacheResponse;
import com.piecepost.grpc.cache.RedisServiceGrpc;
import com.piecepost.grpc.cache.CacheSetRequest;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
public class TestController {
    private final RedisServiceGrpc.RedisServiceBlockingStub redisServiceBlockingStub;
    @Value("${server.port}")
    public String port;

    @GetMapping("/")
    public ResponseEntity<Map<String,String>> index(){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","User service is running at port "+port));
    }
    @GetMapping("/test")
    public ResponseEntity<Map<String,String>> test(){
        CacheSetRequest setRequest = CacheSetRequest.newBuilder().setKey("rack").setValue("20tr").build();
        CacheResponse response = redisServiceBlockingStub.set(setRequest);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","alo " + port));
    }

    @GetMapping("/get")
    public ResponseEntity<Map<String,Object>> get(){
        CacheRequest cacheRequest = CacheRequest.newBuilder().setKey("rack").build();
        CacheResponse response = redisServiceBlockingStub.get(cacheRequest);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message",response.getValue()));
    }
}
