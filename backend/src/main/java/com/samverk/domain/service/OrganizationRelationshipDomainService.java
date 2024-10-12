/**
 * Provides domain service methods for managing organization relationships.
 * This service handles CRUD operations for organization relationships, including
 * retrieving paginated lists of relationships, fetching relationships by IDs,
 * creating new relationships, updating existing relationships, and deleting
 * relationships.
 */
package com.samverk.domain.service;

import com.samverk.domain.model.OrganizationRelationship;
import com.samverk.domain.repository.OrganizationRelationshipRepository;
import com.samverk.util.Log;
import com.samverk.util.ErrorMessage;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OrganizationRelationshipDomainService {
    private final OrganizationRelationshipRepository organizationRelationshipRepository;

    public OrganizationRelationshipDomainService(OrganizationRelationshipRepository organizationRelationshipRepository) {
        this.organizationRelationshipRepository = organizationRelationshipRepository;
    }
    
    public List<OrganizationRelationship> getAllOrganizationRelationships() {
        Log.info("Fetching all organization relationships");
        return organizationRelationshipRepository.findAll();
    }

    @Cacheable(value = "organizationRelationships", key = "#contractorId + '-' + #subcontractorId")
    public OrganizationRelationship getOrganizationRelationshipById(UUID contractorId, UUID subcontractorId) {
        Log.info("Fetching organization relationship with contractorId: " + contractorId + " and subcontractorId: " + subcontractorId);
        return organizationRelationshipRepository.findById(new OrganizationRelationship.OrganizationRelationshipId(contractorId, subcontractorId))
                .orElseThrow(() -> {
                    Log.error(ErrorMessage.ORGANIZATION_RELATIONSHIP_NOT_FOUND);
                    return new RuntimeException(ErrorMessage.ORGANIZATION_RELATIONSHIP_NOT_FOUND);
                });
    }

    @Cacheable(value = "organizationRelationships", key = "'contractor-' + #contractorId")
    public List<OrganizationRelationship> getOrganizationRelationshipsByContractorId(UUID contractorId) {
        Log.info("Fetching organization relationships for contractor id: " + contractorId);
        return organizationRelationshipRepository.findByContractorId(contractorId);
    }

    @Cacheable(value = "organizationRelationships", key = "'subcontractor-' + #subcontractorId")
    public List<OrganizationRelationship> getOrganizationRelationshipsBySubcontractorId(UUID subcontractorId) {
        Log.info("Fetching organization relationships for subcontractor id: " + subcontractorId);
        return organizationRelationshipRepository.findBySubcontractorId(subcontractorId);
    }

    @Transactional
    public OrganizationRelationship createOrganizationRelationship(OrganizationRelationship organizationRelationship) {
        Log.info("Creating new organization relationship");
        validateOrganizationRelationship(organizationRelationship);
        return organizationRelationshipRepository.save(organizationRelationship);
    }

    @Transactional
    public OrganizationRelationship updateOrganizationRelationship(UUID contractorId, UUID subcontractorId, OrganizationRelationship organizationRelationship) {
        Log.info("Updating organization relationship with contractorId: " + contractorId + " and subcontractorId: " + subcontractorId);
        OrganizationRelationship existingRelationship = getOrganizationRelationshipById(contractorId, subcontractorId);
        validateOrganizationRelationship(organizationRelationship);
        // Update fields as necessary
        return organizationRelationshipRepository.save(existingRelationship);
    }

    @Transactional
    public void deleteOrganizationRelationship(UUID contractorId, UUID subcontractorId) {
        Log.info("Deleting organization relationship with contractorId: " + contractorId + " and subcontractorId: " + subcontractorId);
        organizationRelationshipRepository.deleteById(new OrganizationRelationship.OrganizationRelationshipId(contractorId, subcontractorId));
    }

    private void validateOrganizationRelationship(OrganizationRelationship organizationRelationship) {
        if (organizationRelationship.getContractor() == null || organizationRelationship.getSubcontractor() == null) {
            throw new IllegalArgumentException("Contractor and Subcontractor must not be null");
        }
        // Add more validation as needed
    }
}