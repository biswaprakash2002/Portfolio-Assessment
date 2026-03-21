package com.portfolio.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.dto.request.*;
import com.portfolio.dto.response.*;
import com.portfolio.entity.*;
import com.portfolio.exception.PortfolioException;
import com.portfolio.repository.*;
import com.portfolio.service.SkillService;
import com.portfolio.dto.ProficiencyLevel;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SkillServiceImp implements SkillService {

	private final SkillRepository skillRepository;
	private final SkillCategoryRepository categoryRepository;

	public SkillServiceImp(SkillRepository skillRepository, SkillCategoryRepository categoryRepository) {
		this.skillRepository = skillRepository;
		this.categoryRepository = categoryRepository;
	}

	// ===============================
	// CREATE SKILL
	// ===============================
	@Override
	public SkillResponseDto createSkill(SkillRequestDto dto, MultipartFile file) throws PortfolioException {

		if (dto.getSkillName() == null || dto.getSkillName().trim().isEmpty()) {
			throw new PortfolioException("Skill.NAME_REQUIRED");
		}

		if (dto.getCategoryId() == null) {
			throw new PortfolioException("Skill.CATEGORY_REQUIRED");
		}

		SkillCategoryEntity category = categoryRepository.findById(dto.getCategoryId())
				.orElseThrow(() -> new PortfolioException("Category.NOT_FOUND"));

		if (skillRepository.existsBySkillNameIgnoreCaseAndCategory_Id(dto.getSkillName().trim(), dto.getCategoryId())) {
			throw new PortfolioException("Skill.ALREADY_EXISTS");
		}

		byte[] imageBytes = null;

		try {
			if (file != null && !file.isEmpty()) {
				imageBytes = file.getBytes(); // ✅ FILE → BYTE[]
			}
		} catch (Exception e) {
			throw new PortfolioException("Image.UPLOAD_FAILED");
		}

		ProficiencyLevel level = null;
		try {
			if (dto.getProficiencyLevel() != null) {
				level = ProficiencyLevel.valueOf(dto.getProficiencyLevel().toUpperCase());
			}
		} catch (Exception e) {
			throw new PortfolioException("Skill.INVALID_PROFICIENCY");
		}

		SkillEntity skill = SkillEntity.builder().skillName(dto.getSkillName().trim()).proficiencyLevel(level)
				.proficiencyPercentage(dto.getProficiencyPercentage())
				.experienceYears(dto.getExperienceYears() != null ? BigDecimal.valueOf(dto.getExperienceYears()) : null)
				.iconBlob(imageBytes) // ✅ SAVE FILE
				.displayOrder(dto.getDisplayOrder()).category(category).isActive(true).build();

		skillRepository.save(skill);

		return mapToResponse(skill);
	}

	// ===============================
	// GET ALL SKILLS
	// ===============================
	@Override
	public List<SkillResponseDto> getAllSkills() throws PortfolioException {

		List<SkillEntity> skills = skillRepository.findAll();

		if (skills.isEmpty()) {
			throw new PortfolioException("Skill.NOT_FOUND");
		}

		return skills.stream().map(this::mapToResponse).collect(Collectors.toList());
	}

	// ===============================
	// CREATE CATEGORY
	// ===============================
	@Override
	public SkillCategoryResponseDto createCategory(SkillCategoryRequestDto dto) throws PortfolioException {

		if (dto.getName() == null || dto.getName().trim().isEmpty()) {
			throw new PortfolioException("Category.NAME_REQUIRED");
		}

		// 🔥 Duplicate Check
		if (categoryRepository.existsByNameIgnoreCase(dto.getName().trim())) {
			throw new PortfolioException("Category.ALREADY_EXISTS");
		}

		SkillCategoryEntity category = SkillCategoryEntity.builder().name(dto.getName().trim())
				.description(dto.getDescription()).isActive(true).build();

		categoryRepository.save(category);

		SkillCategoryResponseDto res = new SkillCategoryResponseDto();
		res.setId(category.getId());
		res.setName(category.getName());
		res.setDescription(category.getDescription());

		return res;
	}

	// ===============================
	// GET ALL CATEGORIES
	// ===============================
	@Override
	public List<SkillCategoryResponseDto> getAllCategories() throws PortfolioException {

		List<SkillCategoryEntity> categories = categoryRepository.findAll();

		if (categories.isEmpty()) {
			throw new PortfolioException("Category.NOT_FOUND");
		}

		return categories.stream().map(cat -> {
			SkillCategoryResponseDto dto = new SkillCategoryResponseDto();
			dto.setId(cat.getId());
			dto.setName(cat.getName());
			dto.setDescription(cat.getDescription());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public SkillCategoryResponseDto updateCategory(Long id, SkillCategoryRequestDto dto) throws PortfolioException {

		SkillCategoryEntity category = categoryRepository.findById(id)
				.orElseThrow(() -> new PortfolioException("Category.NOT_FOUND"));

		// Validate name
		if (dto.getName() == null || dto.getName().trim().isEmpty()) {
			throw new PortfolioException("Category.NAME_REQUIRED");
		}

		// Duplicate check (exclude same ID)
		if (!category.getName().equalsIgnoreCase(dto.getName().trim())
				&& categoryRepository.existsByNameIgnoreCase(dto.getName().trim())) {
			throw new PortfolioException("Category.ALREADY_EXISTS");
		}

		category.setName(dto.getName().trim());
		category.setDescription(dto.getDescription());

		categoryRepository.save(category);

		SkillCategoryResponseDto res = new SkillCategoryResponseDto();
		res.setId(category.getId());
		res.setName(category.getName());
		res.setDescription(category.getDescription());

		return res;
	}

	@Override
	public SkillResponseDto updateSkill(Long id, SkillRequestDto dto, MultipartFile file) throws PortfolioException {

		SkillEntity skill = skillRepository.findById(id).orElseThrow(() -> new PortfolioException("Skill.NOT_FOUND"));

		if (dto.getSkillName() == null || dto.getSkillName().trim().isEmpty()) {
			throw new PortfolioException("Skill.NAME_REQUIRED");
		}

		if (dto.getCategoryId() == null) {
			throw new PortfolioException("Skill.CATEGORY_REQUIRED");
		}

		SkillCategoryEntity category = categoryRepository.findById(dto.getCategoryId())
				.orElseThrow(() -> new PortfolioException("Category.NOT_FOUND"));

		boolean isDuplicate = skillRepository.existsBySkillNameIgnoreCaseAndCategory_Id(dto.getSkillName().trim(),
				dto.getCategoryId());

		if (isDuplicate && !(skill.getSkillName().equalsIgnoreCase(dto.getSkillName().trim())
				&& skill.getCategory().getId().equals(dto.getCategoryId()))) {

			throw new PortfolioException("Skill.ALREADY_EXISTS");
		}

		// ✅ FILE UPDATE
		try {
			if (file != null && !file.isEmpty()) {
				skill.setIconBlob(file.getBytes());
			}
		} catch (Exception e) {
			throw new PortfolioException("Image.UPLOAD_FAILED");
		}

		// ENUM
		try {
			if (dto.getProficiencyLevel() != null) {
				skill.setProficiencyLevel(ProficiencyLevel.valueOf(dto.getProficiencyLevel().toUpperCase()));
			}
		} catch (Exception e) {
			throw new PortfolioException("Skill.INVALID_PROFICIENCY");
		}

		skill.setSkillName(dto.getSkillName().trim());
		skill.setProficiencyPercentage(dto.getProficiencyPercentage());
		skill.setExperienceYears(
				dto.getExperienceYears() != null ? BigDecimal.valueOf(dto.getExperienceYears()) : null);
		skill.setDisplayOrder(dto.getDisplayOrder());
		skill.setCategory(category);

		skillRepository.save(skill);

		return mapToResponse(skill);
	}

	// ===============================
	// COMMON MAPPER
	// ===============================
	private SkillResponseDto mapToResponse(SkillEntity skill) {

		SkillResponseDto dto = new SkillResponseDto();

		dto.setId(skill.getId());
		dto.setSkillName(skill.getSkillName());

		dto.setProficiencyLevel(skill.getProficiencyLevel() != null ? skill.getProficiencyLevel().name() : null);

		dto.setProficiencyPercentage(skill.getProficiencyPercentage());

		dto.setExperienceYears(skill.getExperienceYears() != null ? skill.getExperienceYears().doubleValue() : null);

		dto.setDisplayOrder(skill.getDisplayOrder());

		if (skill.getCategory() != null) {
			dto.setCategoryId(skill.getCategory().getId());
			dto.setCategoryName(skill.getCategory().getName());
		}

		// 🔴 BLOB → Base64
		if (skill.getIconBlob() != null) {
			dto.setIconBase64(Base64.getEncoder().encodeToString(skill.getIconBlob()));
		}

		return dto;
	}
}