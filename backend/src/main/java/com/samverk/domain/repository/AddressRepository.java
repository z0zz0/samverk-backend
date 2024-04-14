package com.samverk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.samverk.domain.model.Address;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    // Find addresses by country code if relevant
    List<Address> findByCountryCode(String countryCode);
}
