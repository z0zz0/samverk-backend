package com.samverk.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA compatibility
@RequiredArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @NonNull
    @Column(nullable = false)
    private String role;
}
