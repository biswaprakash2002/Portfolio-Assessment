package com.portfolio.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

import com.portfolio.dto.request.*;
import com.portfolio.dto.response.*;
import com.portfolio.exception.PortfolioException;
import com.portfolio.service.SkillService;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin("*")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    // ✅ Create Skill
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SkillResponseDto> createSkill(
            @ModelAttribute SkillRequestDto dto,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws PortfolioException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(skillService.createSkill(dto, file));
    }

    // ✅ Get All Skills
    @GetMapping
    public ResponseEntity<List<SkillResponseDto>> getAllSkills() throws PortfolioException{
        return ResponseEntity.ok(skillService.getAllSkills());
    }

    // ✅ Create Category
    @PostMapping("/category")
    public ResponseEntity<SkillCategoryResponseDto> createCategory(
            @Valid @RequestBody SkillCategoryRequestDto dto) throws PortfolioException{

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(skillService.createCategory(dto));
    }

    // ✅ Get Categories
    @GetMapping("/category")
    public ResponseEntity<List<SkillCategoryResponseDto>> getAllCategories() throws PortfolioException{
        return ResponseEntity.ok(skillService.getAllCategories());
    }
    
    // UPDATE SKILL
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SkillResponseDto> updateSkill(
            @PathVariable Long id,
            @ModelAttribute SkillRequestDto dto,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws PortfolioException {

        return ResponseEntity.ok(skillService.updateSkill(id, dto, file));
    }

    // UPDATE CATEGORY
    @PutMapping("/category/{id}")
    public ResponseEntity<SkillCategoryResponseDto> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody SkillCategoryRequestDto dto) throws PortfolioException {

        return ResponseEntity.ok(skillService.updateCategory(id, dto));
    }
}