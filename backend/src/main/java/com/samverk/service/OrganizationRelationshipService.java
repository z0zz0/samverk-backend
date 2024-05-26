package com.samverk.service;

import com.samverk.domain.model.OrganizationRelationship;
import com.samverk.domain.repository.OrganizationRelationshipRepository;
import com.samverk.util.ErrorMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrganizationRelationshipService {

    @Autowired
    private OrganizationRelationshipRepository organizationRelationshipRepository;

    public List<OrganizationRelationship> getAllOrganizationRelationships() {
        return organizationRelationshipRepository.findAll();
    }

    public List<OrganizationRelationship> getOrganizationRelationshipsByContractorId(UUID contractorId) {
        return organizationRelationshipRepository.findByContractorId(contractorId);
    }

    public List<OrganizationRelationship> getOrganizationRelationshipsBySubcontractorId(UUID subcontractorId) {
        return organizationRelationshipRepository.findBySubcontractorId(subcontractorId);
    }

    public OrganizationRelationship getOrganizationRelationshipById(UUID contractorId, UUID subcontractorId) {
        return organizationRelationshipRepository
                .findById(new OrganizationRelationship.OrganizationRelationshipId(contractorId, subcontractorId))
                .orElseThrow(() -> new RuntimeException(ErrorMessage.ORGANIZATION_RELATIONSHIP_NOT_FOUND));
    }
    
    public OrganizationRelationship createOrganizationRelationship(OrganizationRelationship organizationRelationship) {
        return organizationRelationshipRepository.save(organizationRelationship);
     }
    
     // TODO: IMPLEMENT THESE FUNCTIONS BUT THINK ABOUT THE LOGIC, WE MIGHT NOT WANT TO DELETE BUT MARK IT AS INACTIVE. CHANGE THE TABLES (SQL-SCRIPT) TO REFLECT THIS.

    // public void deleteOrganizationRelationship(UUID contractorId, UUID subcontractorId) {
    //     organizationRelationshipRepository.deleteById(new OrganizationRelationship.OrganizationRelationshipId(contractorId, subcontractorId));
    // }
}
