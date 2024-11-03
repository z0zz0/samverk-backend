package com.samverk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samverk.domain.entity.Organization;

import java.util.UUID;
import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
    
    /**
     * Finds all organizations of the specified type.
     *
     * @param type the type of organizations to find
     * @return a list of organizations of the specified type
     */
    List<Organization> findByOrganizationType(String type);

    /**
     * Checks if an organization with the given organization number exists in the repository.
     *
     * @param organizationNumber the organization number to check for
     * @return true if an organization with the given organization number exists, false otherwise
     */
    boolean existsByOrganizationNumber(String organizationNumber);
}
