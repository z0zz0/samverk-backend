package com.samverk.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddressDTO {
    private UUID addressId;
    private String streetAddress;
    private String postalCode;
    private String municipality;
    private String stateProvince;
    private String countryCode;
}
