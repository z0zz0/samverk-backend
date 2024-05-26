package com.samverk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.samverk.domain.model.Organization;
import java.util.UUID;
import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
    // Example custom query to find organizations by type
    List<Organization> findByOrganizationType(String type);
}
