package com.samverk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddressDTO {
    private UUID addressId;

    @NotBlank(message = "Street address is required")
    @Size(min = 2, max = 50, message = "Street address must be between 2 and 50 characters")
    private String streetAddress;

    @NotBlank(message = "Postal code is required")
    @Size(min = 2, max = 25, message = "Street address must be between 2 and 25 characters")
    private String postalCode;

    @NotBlank(message = "Municipality is required")
    @Size(min = 2, max = 25, message = "Municipality must be between 2 and 25 characters")
    private String municipality;

    @Size(max = 25, message = "State/Province cannot exceed 25 characters")
    private String stateProvince;

    @NotBlank(message = "Country code is required")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Country code must be 2 uppercase letters (ISO 3166-1 alpha-2)")
    private String countryCode;
}
