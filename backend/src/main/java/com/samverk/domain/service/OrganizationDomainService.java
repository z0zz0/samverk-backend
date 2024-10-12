package com.samverk.domain.service;

import com.samverk.domain.entity.Organization;
import com.samverk.domain.repository.OrganizationRepository;
import com.samverk.util.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OrganizationDomainService {
    private final OrganizationRepository organizationRepository;

    public OrganizationDomainService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public List<Organization> getAllOrganizations() {
        Log.info("Fetching all organizations");
        return organizationRepository.findAll();
    }

    public Organization getOrganizationById(UUID organizationId) {
        Log.info("Fetching organization with id: " + organizationId);
        return organizationRepository.findById(organizationId)
                .orElseThrow(() -> {
                    Log.error("Organization not found with id: " + organizationId);
                    return new RuntimeException("Organization not found");
                });
    }

    @Transactional
    public Organization createOrganization(Organization organization) {
        Log.info("Creating new organization");
        return organizationRepository.save(organization);
    }

    @Transactional
    public Organization updateOrganization(UUID organizationId, Organization organizationDetails) {
        Log.info("Updating organization with id: " + organizationId);
        Organization organization = getOrganizationById(organizationId);
        organization.setOrganizationNumber(organizationDetails.getOrganizationNumber());
        organization.setOrganizationType(organizationDetails.getOrganizationType());
        organization.setOrganizationName(organizationDetails.getOrganizationName());
        return organizationRepository.save(organization);
    }

    @Transactional
    public void deleteOrganization(UUID organizationId) {
        Log.info("Deleting organization with id: " + organizationId);
        organizationRepository.deleteById(organizationId);
    }
}
