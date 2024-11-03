package com.samverk.domain.service;

import com.samverk.domain.exception.DuplicateOrganizationException;
import com.samverk.domain.exception.OrganizationNotFoundException;
import com.samverk.domain.entity.Organization;
import com.samverk.domain.repository.OrganizationRepository;
import com.samverk.util.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationDomainService {

    private final OrganizationRepository organizationRepository;

    public OrganizationDomainService(OrganizationRepository organizationRepository) 
    {
        this.organizationRepository = organizationRepository;
    }

    public List<Organization> getAllOrganizations() 
    {
        return organizationRepository.findAll();
    }

    public Optional<Organization> getOrganizationById(UUID id) 
    {
        return organizationRepository.findById(id);
    }

    @Transactional
    public Organization createOrganization(Organization organization) 
    {
        if (organizationRepository.existsByOrganizationNumber(organization.getOrganizationNumber())) 
        {
            Log.warn("Attempted to create duplicate organization with number: {}", organization.getOrganizationNumber());
            throw new DuplicateOrganizationException("An organization with this number already exists.");
        }

        return organizationRepository.save(organization);
    }

    @Transactional
    public Organization updateOrganization(UUID organizationId, Organization organizationDetails) 
    {
        Organization existingOrganization = organizationRepository.findById(organizationId).orElseThrow(() -> {
            Log.error("Organization not found with id: {}", organizationId);
            return new OrganizationNotFoundException("Organization not found with id: " + organizationId);
        });
                
        if (!existingOrganization.getOrganizationNumber().equals(organizationDetails.getOrganizationNumber())
                && organizationRepository.existsByOrganizationNumber(organizationDetails.getOrganizationNumber())) 
        {
            Log.warn("Attempted to update organization to duplicate number: {}", organizationDetails.getOrganizationNumber());

            throw new DuplicateOrganizationException("An organization with this number already exists.");
        }

        existingOrganization.setOrganizationName(organizationDetails.getOrganizationName());
        existingOrganization.setOrganizationNumber(organizationDetails.getOrganizationNumber());
        existingOrganization.setOrganizationType(organizationDetails.getOrganizationType());
        existingOrganization.setAddress(organizationDetails.getAddress());

        return organizationRepository.save(existingOrganization);
    }

    @Transactional
    public void deleteOrganization(UUID organizationId) 
    {
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> {
                    Log.error("Organization not found with id: {}", organizationId);
                    return new OrganizationNotFoundException("Organization not found with id: " + organizationId);
                });

        organizationRepository.delete(organization);
    }
}
