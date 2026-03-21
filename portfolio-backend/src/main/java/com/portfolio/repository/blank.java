package com.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.entity.RoleEntity;

@Repository
public interface blank extends JpaRepository<RoleEntity, Long>{

	 boolean existsByRoleName(String roleName);

	 Optional<RoleEntity> findByRoleName(String roleName);

	}
