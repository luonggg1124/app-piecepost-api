package com.piecepost.user.models.extend;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class TimestampSimple {
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
