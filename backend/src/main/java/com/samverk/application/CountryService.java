package com.samverk.application;

import com.samverk.domain.entity.Country;
import com.samverk.domain.service.CountryDomainService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private final CountryDomainService countryDomainService;

    public CountryService(CountryDomainService countryDomainService) {
        this.countryDomainService = countryDomainService;
    }

    public List<Country> getAllCountries() {
        return countryDomainService.getAllCountries();
    }

    public Country getCountryByCode(String countryCode) {
        return countryDomainService.getCountryByCode(countryCode);
    }

    public Country createCountry(Country country) {
        return countryDomainService.createCountry(country);
    }

    public Country updateCountry(String countryCode, Country countryDetails) {
        return countryDomainService.updateCountry(countryCode, countryDetails);
    }

    public void deleteCountry(String countryCode) {
        countryDomainService.deleteCountry(countryCode);
    }
}