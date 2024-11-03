package com.samverk.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import com.samverk.domain.entity.Address;
import com.samverk.domain.entity.Country;
import com.samverk.domain.exception.CountryNotFoundException;
import com.samverk.domain.repository.CountryRepository;
import com.samverk.dto.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public abstract class AddressMapper {
    
    @Autowired
    private CountryRepository countryRepository;

    @Mapping(target = "country", source = "countryCode", qualifiedByName = "countryCodeToCountry")
    public abstract Address toEntity(AddressDTO dto);

    @Mapping(target = "countryCode", source = "country.countryCode")
    public abstract AddressDTO toDto(Address entity);

    @Named("countryCodeToCountry")
    protected Country countryCodeToCountry(String countryCode) {
        return countryRepository.findById(countryCode)
                .orElseThrow(() -> new CountryNotFoundException("Country not found for code: " + countryCode));
    }
}
