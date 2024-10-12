package com.samverk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samverk.domain.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Find roles by name (assuming roles can be retrieved by their names)
    Role findByName(String name);
}
