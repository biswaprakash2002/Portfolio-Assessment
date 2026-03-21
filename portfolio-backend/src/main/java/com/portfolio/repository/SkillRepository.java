package com.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.entity.SkillEntity;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long> {

	boolean existsBySkillNameIgnoreCaseAndCategory_Id(String skillName, Long categoryId);


}
