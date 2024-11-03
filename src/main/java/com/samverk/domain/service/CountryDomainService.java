package com.samverk.domain.service;

import com.samverk.domain.entity.Country;
import com.samverk.domain.exception.CountryNotFoundException;
import com.samverk.domain.exception.InvalidCountryCodeException;
import com.samverk.domain.repository.CountryRepository;
import com.samverk.util.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryDomainService {
    private final CountryRepository countryRepository;

    public CountryDomainService(CountryRepository countryRepository) 
    {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() 
    {
        Log.info("Fetching all countries");
        return countryRepository.findAll();
    }

    public Country getCountryByCode(String countryCode) 
    {
        if (countryCode == null || countryCode.trim().isEmpty()) 
        {
            Log.error("Country code is null or empty");
            throw new InvalidCountryCodeException("Country code must not be null or empty");
        }

        Log.info("Fetching country with code: {}", countryCode);
        return countryRepository.findById(countryCode)
                .orElseThrow(() -> {
                    Log.error("Country not found with code: {}", countryCode);
                    return new CountryNotFoundException("Country not found for code: " + countryCode);
                });
    }

    @Transactional
    public Country createCountry(Country country) 
    {
        if (country == null) 
        {
            Log.error("Country object is null");
            throw new IllegalArgumentException("Country must not be null");
        }

        Log.info("Creating new country with code: {}", country.getCountryCode());
        return countryRepository.save(country);
    }

    @Transactional
    public Country updateCountry(String countryCode, Country countryDetails) 
    {
        if (countryDetails == null) 
        {
            Log.error("Country details are null");
            throw new IllegalArgumentException("Country details must not be null");
        }

        Log.info("Updating country with code: {}", countryCode);
        Country country = getCountryByCode(countryCode);

        country.setCountryName(countryDetails.getCountryName());

        return countryRepository.save(country);
    }

    @Transactional
    public void deleteCountry(String countryCode) 
    {
        if (countryCode == null || countryCode.trim().isEmpty()) 
        {
            Log.error("Country code is null or empty");
            throw new InvalidCountryCodeException("Country code must not be null or empty");
        }

        Log.info("Deleting country with code: {}", countryCode);

        Country country = getCountryByCode(countryCode);

        countryRepository.delete(country);
    }
}
