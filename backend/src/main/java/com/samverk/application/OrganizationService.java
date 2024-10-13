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
    
    public OrganizationService(OrganizationDomainService organizationDomainService, OrganizationMapper organizationMapper) {
        this.organizationDomainService = organizationDomainService;
        this.organizationMapper = organizationMapper;
    }

    public List<Organization> getAllOrganizations() {
        return organizationDomainService.getAllOrganizations();
    }

    public Organization getOrganizationById(UUID organizationId) {
        return organizationDomainService.getOrganizationById(organizationId);
    }

    public Organization createOrganization(OrganizationDTO organization) {
        return organizationDomainService.createOrganization(organizationMapper.toEntity(organization));
    }

    public Organization updateOrganization(UUID organizationId, Organization organizationDetails) {
        return organizationDomainService.updateOrganization(organizationId, organizationDetails);
    }

    public void deleteOrganization(UUID organizationId) {
        organizationDomainService.deleteOrganization(organizationId);
    }
}