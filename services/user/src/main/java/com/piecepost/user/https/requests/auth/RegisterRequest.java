package com.piecepost.user.https.requests.auth;

import com.piecepost.user.models.json.IProfile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RegisterRequest {
    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "Password is required.")
    private String password;

    @Valid
    private IProfile profile;

    private String deviceId;
    private String otp;
}
