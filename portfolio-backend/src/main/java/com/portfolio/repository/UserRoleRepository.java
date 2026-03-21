package com.portfolio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.entity.UserEntity;
import com.portfolio.entity.UserRoleEntity;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    // ✅ Check if role already assigned
    boolean existsByUser_IdAndRole_Id(Long userId, Long roleId);

    // ✅ Get all roles of a user (USED IN LOGIN 🔥)
    List<UserRoleEntity> findAllByUser(UserEntity user);

    // ✅ Optional (if single role logic used)
    Optional<UserRoleEntity> findByUser(UserEntity user);
}