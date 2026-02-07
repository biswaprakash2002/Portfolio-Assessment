package com.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.entity.UserEntity;
import com.portfolio.entity.UserRoleEntity;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long>{
	
	 boolean existsByUser_IdAndRole_Id(Long userId, Long roleId);

	
}
