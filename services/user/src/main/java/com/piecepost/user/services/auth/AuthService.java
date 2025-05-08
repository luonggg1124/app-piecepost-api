package com.piecepost.user.services.auth;

import org.springframework.messaging.MessagingException;

import com.piecepost.user.https.responses.auth.LoginResponse;

public interface AuthService {
    public void sendVerificationCode(String email) throws MessagingException;
    // public LoginResponse register();
}
