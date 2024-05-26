package com.samverk.service;

import com.samverk.domain.model.Country;
import com.samverk.domain.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryByCode(String countryCode) {
        return countryRepository.findById(countryCode)
                .orElseThrow(() -> new RuntimeException("Country not found"));
    }

    public Country createCountry(Country country) {
        return countryRepository.save(country);
    }

    public Country updateCountry(String countryCode, Country countryDetails) {
        Country country = getCountryByCode(countryCode);
        country.setCountryName(countryDetails.getCountryName());
        // Update other fields as necessary
        return countryRepository.save(country);
    }

    public void deleteCountry(String countryCode) {
        countryRepository.deleteById(countryCode);
    }
}
