package com.piecepost.user.https.responses.auth;

import com.piecepost.user.models.entities.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private User user;
    private String deviceId;
}
