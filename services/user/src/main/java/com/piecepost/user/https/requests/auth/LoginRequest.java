package com.piecepost.user.https.requests.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginRequest {
    @NotBlank(message = "User name or email is required")
    private String emailOrUsername;
    @NotBlank(message = "Password is required")
    private String password;
    private String deviceId;
}
