package com.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	boolean existsByUsername(String username);

    boolean existsByEmail(String email);

	UserEntity getUserById(Long userId);

	Optional<UserEntity> findByUsername(String username);
    
}
