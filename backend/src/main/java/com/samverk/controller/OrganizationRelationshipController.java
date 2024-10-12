package com.samverk.controller;

import com.samverk.application.OrganizationRelationshipService;
import com.samverk.domain.entity.OrganizationRelationship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/organization-relationships")
public class OrganizationRelationshipController {
    @Autowired
    private OrganizationRelationshipService organizationRelationshipService;

    @GetMapping
    public List<OrganizationRelationship> getAllOrganizationRelationships() {
        return organizationRelationshipService.getAllOrganizationRelationships();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationRelationship> getOrganizationRelationshipById(@PathVariable UUID contractorId, @PathVariable UUID subcontractorId) {
        return ResponseEntity.ok(organizationRelationshipService.getOrganizationRelationshipById(contractorId,subcontractorId));
    }

    @PostMapping
    public ResponseEntity<OrganizationRelationship> createOrganizationRelationship(@RequestBody OrganizationRelationship organizationRelationship) {
        return new ResponseEntity<>(
                organizationRelationshipService.createOrganizationRelationship(organizationRelationship),
                HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<OrganizationRelationship> updateOrganizationRelationship(@PathVariable UUID contractorId, @PathVariable UUID subcontractorId, @RequestBody OrganizationRelationship organizationRelationshipDetails) {
//        return ResponseEntity.ok(
//                organizationRelationshipService.updateOrganizationRelationship(
//                    contractorId,
//                    subcontractorId,
//                    organizationRelationshipDetails));
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteOrganizationRelationship(@PathVariable UUID contractorId, @PathVariable UUID subcontractorId) {
//        organizationRelationshipService.deleteOrganizationRelationship(contractorId, subcontractorId);
//        return ResponseEntity.noContent().build();
//    }
}
