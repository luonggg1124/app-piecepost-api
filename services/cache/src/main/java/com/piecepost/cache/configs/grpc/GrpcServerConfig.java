package com.piecepost.cache.configs.grpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.piecepost.cache.grpc.redis.RedisGrpcService;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import org.springframework.beans.factory.annotation.Value;

@Configuration
public class GrpcServerConfig {
    @Value("${grpc.server.port}")
    private int port;
    @Bean
    public Server grpcServer(RedisGrpcService cacheGrpcService) throws Exception {
        
        return ServerBuilder.forPort(port)
                .addService(cacheGrpcService)  
                .build()
                .start();
    }
}
