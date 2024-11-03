package com.samverk.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrganizationDTO {
    private UUID organizationId;

    @NotBlank(message = "Organization number is required")
    @Pattern(regexp = "^[0-9]{6}-[0-9]{4}$", message = "Organization number must be in format XXXXXX-XXXX")
    private String organizationNumber;

    @NotBlank(message = "Organization type is required")
    @Size(max = 50, message = "Organization type cannot exceed 50 characters")
    private String organizationType;

    @NotBlank(message = "Organization name is required")
    @Size(min = 2, max = 100, message = "Organization name must be between 2 and 100 characters")
    private String organizationName;

    @NotNull(message = "Address is required")
    @Valid
    private AddressDTO address;
}
