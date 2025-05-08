package com.piecepost.user.configs.grpc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.piecepost.grpc.cache.RedisServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Configuration
public class GrpcClientCacheConfig {
    @Value("${app.cache.host}") 
    public String appCacheHost;
    @Value("${app.cache.port}") 
    public int appCachePort;

    @Bean
    public ManagedChannel cacheChannel() {
        return ManagedChannelBuilder.forAddress(appCacheHost, appCachePort)
                .usePlaintext()
                .build();
    }
    @Bean
    public RedisServiceGrpc.RedisServiceBlockingStub redisServiceBlockingStub(ManagedChannel cacheChannel) {
        return RedisServiceGrpc.newBlockingStub(cacheChannel);
    }
}
