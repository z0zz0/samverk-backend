package com.samverk.application;

import com.samverk.domain.entity.Organization;
import com.samverk.domain.service.OrganizationDomainService;
import com.samverk.dto.OrganizationDTO;
import com.samverk.mapper.OrganizationMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrganizationService {

    private final OrganizationDomainService organizationDomainService;
    private final OrganizationMapper organizationMapper;

    public OrganizationService(OrganizationDomainService organizationDomainService,
            OrganizationMapper organizationMapper) {
        this.organizationDomainService = organizationDomainService;
        this.organizationMapper = organizationMapper;
    }

    public List<OrganizationDTO> getAllOrganizations() {
        List<Organization> organizations = organizationDomainService.getAllOrganizations();
        return organizations.stream()
                .map(organizationMapper::toDto)
                .toList();
    }

    public OrganizationDTO getOrganizationById(UUID organizationId) {
        Organization organization = organizationDomainService.getOrganizationById(organizationId)
                .orElseThrow();
        return organizationMapper.toDto(organization);
    }

    public OrganizationDTO createOrganization(OrganizationDTO organizationDTO) {
        Organization organization = organizationMapper.toEntity(organizationDTO);
        Organization createdOrganization = organizationDomainService.createOrganization(organization);
        return organizationMapper.toDto(createdOrganization);
    }

    public OrganizationDTO updateOrganization(UUID organizationId, OrganizationDTO organizationDTO) {
        Organization organizationDetails = organizationMapper.toEntity(organizationDTO);
        Organization updatedOrganization = organizationDomainService.updateOrganization(organizationId,
                organizationDetails);
        return organizationMapper.toDto(updatedOrganization);
    }

    public void deleteOrganization(UUID organizationId) {
        organizationDomainService.deleteOrganization(organizationId);
    }
}
