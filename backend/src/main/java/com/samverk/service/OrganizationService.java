package com.samverk.service;

import com.samverk.domain.model.Organization;
import com.samverk.domain.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    public Organization getOrganizationById(UUID organizationId) {
        return organizationRepository.findById(organizationId)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
    }

    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    public Organization updateOrganization(UUID organizationId, Organization organizationDetails) {
        Organization organization = getOrganizationById(organizationId);
        organization.setOrganizationNumber(organizationDetails.getOrganizationNumber());
        organization.setOrganizationType(organizationDetails.getOrganizationType());
        organization.setOrganizationName(organizationDetails.getOrganizationName());
        // Update other fields as necessary
        return organizationRepository.save(organization);
    }

    public void deleteOrganization(UUID organizationId) {
        organizationRepository.deleteById(organizationId);
    }
}
