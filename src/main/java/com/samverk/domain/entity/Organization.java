package com.samverk.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import org.hibernate.annotations.GenericGenerator;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA compatibility
@RequiredArgsConstructor
@Entity
public class Organization {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID organizationId;

    @NonNull
    @Column(unique = true, nullable = false)
    private String organizationNumber;
    
    @NonNull
    @Column(nullable = false)
    private String organizationType;
    
    @NonNull
    @Column(unique = true, nullable = false)
    private String organizationName;
    
    @NonNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    
    @Setter(AccessLevel.NONE)
    @Column(updatable = false, nullable = false, insertable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();
}
