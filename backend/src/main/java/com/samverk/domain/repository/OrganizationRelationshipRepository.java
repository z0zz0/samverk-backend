package com.samverk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.samverk.domain.model.OrganizationRelationship;
import com.samverk.domain.model.OrganizationRelationship.OrganizationRelationshipId;

import java.util.UUID;
import java.util.List;

@Repository
public interface OrganizationRelationshipRepository extends JpaRepository<OrganizationRelationship, OrganizationRelationshipId> {
    List<OrganizationRelationship> findByContractorId(UUID contractorId);
    List<OrganizationRelationship> findBySubcontractorId(UUID subcontractorId);
}
