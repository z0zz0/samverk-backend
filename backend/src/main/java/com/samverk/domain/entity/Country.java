package com.samverk.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Country {
    @Id
    private String countryCode;

    @Column(nullable = false)
    private String countryName;
}
