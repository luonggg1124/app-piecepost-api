package com.piecepost.user.services.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import com.piecepost.cache.CacheServiceGrpc;
import com.piecepost.cache.CacheResponse;
import com.piecepost.cache.CacheSetRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class AuthServiceImplement implements AuthService {
    @Value("${grpcHost}")
    private String grpcHost;
    @Value("${grpcPort}")
    private int grpcPort;
    private final ManagedChannel channel;
    private final CacheServiceGrpc.CacheServiceBlockingStub cacheStub;

    public AuthServiceImplement(){
        this.channel = ManagedChannelBuilder.forAddress(grpcHost, grpcPort).build();
        this.cacheStub = CacheServiceGrpc.newBlockingStub(channel);
    }

    public void sendVerificationCode(String email) throws MessagingException{
        String randomCode = String.format(email, null);
        CacheSetRequest setRequest = CacheSetRequest.newBuilder().setKey(randomCode).setValue("1").build();
        CacheResponse response = cacheStub.set(setRequest);
    }
}
