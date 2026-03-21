package com.portfolio.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.portfolio.dto.request.*;
import com.portfolio.dto.response.*;
import com.portfolio.exception.PortfolioException;

public interface SkillService {
	
	SkillResponseDto createSkill(SkillRequestDto dto, MultipartFile file) throws PortfolioException;

    List<SkillResponseDto> getAllSkills() throws PortfolioException;

    SkillCategoryResponseDto createCategory(SkillCategoryRequestDto dto) throws PortfolioException ;

    List<SkillCategoryResponseDto> getAllCategories() throws PortfolioException ;
    

    SkillResponseDto updateSkill(Long id, SkillRequestDto dto, MultipartFile file) throws PortfolioException;
    
    SkillCategoryResponseDto updateCategory(Long id, SkillCategoryRequestDto dto) throws PortfolioException;
}