package com.portfolio.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.entity.RoleEntity;
import com.portfolio.exception.PortfolioException;
import com.portfolio.repository.RoleRepository;
import com.portfolio.service.RoleService;

@Service
@Transactional
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleEntity createRole(RoleEntity role) throws PortfolioException {

        if (roleRepository.existsByRoleName(role.getRoleName())) {
            throw new PortfolioException("Role already exists");
        }

        return roleRepository.save(role);
    }

    @Override
    public RoleEntity getRoleById(Long id) throws PortfolioException {
        return roleRepository.findById(id)
                .orElseThrow(() -> new PortfolioException("Role not found with id: " + id));
    }

    @Override
    public RoleEntity getRoleByName(String roleName) throws PortfolioException {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new PortfolioException("Role not found: " + roleName));
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteRole(Long id) throws PortfolioException {
        RoleEntity role = getRoleById(id);
        role.setIsDeleted(true); // soft delete
        roleRepository.save(role);
    }
}
