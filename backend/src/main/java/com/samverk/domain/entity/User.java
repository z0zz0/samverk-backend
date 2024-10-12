package com.samverk.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.AccessLevel;
import lombok.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.JoinTable;
import jakarta.persistence.FetchType;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Set;
import java.util.HashSet;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA compatibility
@RequiredArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID userId;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @NonNull
    @Column(unique = true, nullable = false)
    private String socialIdentityNumber;

    @NonNull
    @Column(nullable = false)
    private String passwordHash;

    @NonNull
    @Column(nullable = false)
    private LocalDate birthDate;

    @NonNull
    @Column(nullable = false)
    private String firstName;

    @NonNull
    @Column(nullable = false)
    private String surname;
    
    @Column
    private String phonenumber;

    @NonNull
    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String userDescription;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Setter(AccessLevel.NONE)
    @Column(updatable = false, nullable = false, insertable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime creationTime;

    @Nonnull
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Nonnull
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Answer> answers = new HashSet<>();

    @Nonnull
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Request> requests = new HashSet<>();
}
