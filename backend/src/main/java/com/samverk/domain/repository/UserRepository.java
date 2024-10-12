package com.samverk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samverk.domain.entity.User;

import java.util.UUID;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    // Find users by their associated organization
    List<User> findByOrganization_OrganizationId(UUID organizationId);

    User findByUserId(UUID userId);

    User findByEmail(String email);
}
