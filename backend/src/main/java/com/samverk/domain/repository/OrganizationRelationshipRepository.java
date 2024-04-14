package com.samverk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.samverk.domain.model.OrganizationRelationship;
import java.util.List;

@Repository
public interface OrganizationRelationshipRepository extends JpaRepository<OrganizationRelationship, Long> {
    // Find relationships where the specified organization is the contractor
    List<OrganizationRelationship> findByContractorId(Long contractorId);

    // Find relationships where the specified organization is the subcontractor
    List<OrganizationRelationship> findBySubcontractorId(Long subcontractorId);
}
