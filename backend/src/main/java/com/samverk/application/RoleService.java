package com.samverk.application;

import com.samverk.domain.entity.Role;
import com.samverk.domain.service.RoleDomainService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleDomainService roleDomainService;

    public RoleService(RoleDomainService roleDomainService) {
        this.roleDomainService = roleDomainService;
    }

    public List<Role> getAllRoles() {
        return roleDomainService.getAllRoles();
    }

    public Role getRoleById(Long roleId) {
        return roleDomainService.getRoleById(roleId);
    }

    public Role createRole(Role role) {
        return roleDomainService.createRole(role);
    }

    public Role updateRole(Long roleId, Role roleDetails) {
        return roleDomainService.updateRole(roleId, roleDetails);
    }

    public void deleteRole(Long roleId) {
        roleDomainService.deleteRole(roleId);
    }
}