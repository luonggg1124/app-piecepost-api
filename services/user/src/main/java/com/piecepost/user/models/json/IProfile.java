package com.piecepost.user.models.json;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IProfile {
    private String imageUrl;

    @NotBlank(message = "Give name is required.")
    private String givenName;

    @NotBlank(message = "Family name is required.")
    private String familyName;
    private String bio;
    private String location;
    private String website;
}
