package com.samverk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samverk.domain.entity.OrganizationRelationship;
import com.samverk.domain.entity.OrganizationRelationship.OrganizationRelationshipId;

import java.util.UUID;
import java.util.List;

/**
 * Repository interface for managing organization relationships.
 * Provides methods to find organization relationships by contractor ID and subcontractor ID.
 */
@Repository
public interface OrganizationRelationshipRepository extends JpaRepository<OrganizationRelationship, OrganizationRelationshipId> {
    List<OrganizationRelationship> findByContractorId(UUID contractorId);
    List<OrganizationRelationship> findBySubcontractorId(UUID subcontractorId);
}
