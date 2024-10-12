package com.samverk.application;

import com.samverk.domain.model.OrganizationRelationship;
import com.samverk.domain.service.OrganizationRelationshipDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrganizationRelationshipService {
    private final OrganizationRelationshipDomainService organizationRelationshipDomainService;

    public OrganizationRelationshipService(OrganizationRelationshipDomainService organizationRelationshipDomainService) {
        this.organizationRelationshipDomainService = organizationRelationshipDomainService;
    }

    public List<OrganizationRelationship> getAllOrganizationRelationships() {
        return organizationRelationshipDomainService.getAllOrganizationRelationships();
    }

    public List<OrganizationRelationship> getOrganizationRelationshipsByContractorId(UUID contractorId) {
        return organizationRelationshipDomainService.getOrganizationRelationshipsByContractorId(contractorId);
    }

    public List<OrganizationRelationship> getOrganizationRelationshipsBySubcontractorId(UUID subcontractorId) {
        return organizationRelationshipDomainService.getOrganizationRelationshipsBySubcontractorId(subcontractorId);
    }

    public OrganizationRelationship getOrganizationRelationshipById(UUID contractorId, UUID subcontractorId) {
        return organizationRelationshipDomainService.getOrganizationRelationshipById(contractorId, subcontractorId);
    }

    public OrganizationRelationship createOrganizationRelationship(OrganizationRelationship organizationRelationship) {
        return organizationRelationshipDomainService.createOrganizationRelationship(organizationRelationship);
    }

    public void deleteOrganizationRelationship(UUID contractorId, UUID subcontractorId) {
        organizationRelationshipDomainService.deleteOrganizationRelationship(contractorId, subcontractorId);
    }
}