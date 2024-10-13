package com.samverk.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrganizationDTO {
    private UUID organizationId;
    private String organizationNumber;
    private String organizationType;
    private String organizationName;
    private AddressDTO address;
}
