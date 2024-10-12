package com.samverk.domain.service;

import com.samverk.domain.entity.Role;
import com.samverk.domain.repository.RoleRepository;
import com.samverk.util.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleDomainService {
    private final RoleRepository roleRepository;

    public RoleDomainService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        Log.info("Fetching all roles");
        return roleRepository.findAll();
    }

    public Role getRoleById(int roleId) {
        Log.info("Fetching role with id: " + roleId);
        return roleRepository.findById(roleId)
                .orElseThrow(() -> {
                    Log.error("Role not found with id: " + roleId);
                    return new RuntimeException("Role not found");
                });
    }

    @Transactional
    public Role createRole(Role role) {
        Log.info("Creating new role");
        return roleRepository.save(role);
    }

    @Transactional
    public Role updateRole(int roleId, Role roleDetails) {
        Log.info("Updating role with id: " + roleId);
        Role role = getRoleById(roleId);
        role.setRole(roleDetails.getRole());
        return roleRepository.save(role);
    }

    @Transactional
    public void deleteRole(int roleId) {
        Log.info("Deleting role with id: " + roleId);
        roleRepository.deleteById(roleId);
    }
}
