package com.samverk.domain.service;

import com.samverk.domain.entity.Country;
import com.samverk.domain.repository.CountryRepository;
import com.samverk.util.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryDomainService {
    private final CountryRepository countryRepository;

    public CountryDomainService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        Log.info("Fetching all countries");
        return countryRepository.findAll();
    }

    public Country getCountryByCode(String countryCode) {
        Log.info("Fetching country with code: " + countryCode);
        return countryRepository.findById(countryCode)
                .orElseThrow(() -> {
                    Log.error("Country not found with code: " + countryCode);
                    return new RuntimeException("Country not found");
                });
    }

    @Transactional
    public Country createCountry(Country country) {
        Log.info("Creating new country");
        return countryRepository.save(country);
    }

    @Transactional
    public Country updateCountry(String countryCode, Country countryDetails) {
        Log.info("Updating country with code: " + countryCode);
        Country country = getCountryByCode(countryCode);
        country.setCountryName(countryDetails.getCountryName());
        return countryRepository.save(country);
    }

    @Transactional
    public void deleteCountry(String countryCode) {
        Log.info("Deleting country with code: " + countryCode);
        countryRepository.deleteById(countryCode);
    }
}
