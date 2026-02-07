package com.portfolio.service;

import java.util.List;

import com.portfolio.entity.RoleEntity;
import com.portfolio.exception.PortfolioException;

public interface RoleService {

    RoleEntity createRole(RoleEntity role) throws PortfolioException;

    RoleEntity getRoleById(Long id) throws PortfolioException;

    RoleEntity getRoleByName(String roleName) throws PortfolioException;

    List<RoleEntity> getAllRoles();

    void deleteRole(Long id) throws PortfolioException;
}
