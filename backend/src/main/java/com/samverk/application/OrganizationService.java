package com.samverk.application;

import com.samverk.domain.entity.Organization;
import com.samverk.domain.service.OrganizationDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrganizationService {
    private final OrganizationDomainService organizationDomainService;

    public OrganizationService(OrganizationDomainService organizationDomainService) {
        this.organizationDomainService = organizationDomainService;
    }

    public List<Organization> getAllOrganizations() {
        return organizationDomainService.getAllOrganizations();
    }

    public Organization getOrganizationById(UUID organizationId) {
        return organizationDomainService.getOrganizationById(organizationId);
    }

    public Organization createOrganization(Organization organization) {
        return organizationDomainService.createOrganization(organization);
    }

    public Organization updateOrganization(UUID organizationId, Organization organizationDetails) {
        return organizationDomainService.updateOrganization(organizationId, organizationDetails);
    }

    public void deleteOrganization(UUID organizationId) {
        organizationDomainService.deleteOrganization(organizationId);
    }
}