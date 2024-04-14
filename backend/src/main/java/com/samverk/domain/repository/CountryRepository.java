package com.samverk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.samverk.domain.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    // No custom methods needed, using JpaRepository default methods only
}
