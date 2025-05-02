package com.piecepost.user.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.piecepost.user.models.enums.ProviderOauth;
import com.piecepost.user.models.enums.UserRole;
import com.piecepost.user.models.extend.Timestamp;
import com.piecepost.user.models.json.IProfile;
import com.piecepost.user.models.json.ISetting;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email", "provider_id"}))
public class User extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 100, nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = true)
    @JsonIgnore
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Column(name = "is_verified", nullable = false)
    private boolean isVerified = false;

    @Column(name = "is_banned", nullable = false)
    private boolean isBanned = false;

    @Type(JsonType.class)
    @Column(name = "settings", columnDefinition = "jsonb")
    private ISetting settings;

    @Type(JsonType.class)
    @Column(name = "profile", columnDefinition = "jsonb")
    private IProfile profile;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider_oauth", nullable = false)
    private ProviderOauth providerOauth = ProviderOauth.CREDENTIALS;

    @Column(name = "provider_id")
    private String providerId;

}
