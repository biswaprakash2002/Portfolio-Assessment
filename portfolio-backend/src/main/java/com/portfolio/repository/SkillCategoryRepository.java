package com.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.entity.SkillCategoryEntity;

@Repository
public interface SkillCategoryRepository extends JpaRepository<SkillCategoryEntity, Long> {

	boolean existsByNameIgnoreCase(String name);
	


}
