package com.portfolio.dto.response;

import lombok.Data;

@Data
public class SkillResponseDto {

    private Long id;
    private String skillName;
    private String proficiencyLevel;
    private Integer proficiencyPercentage;
    private Double experienceYears;
    private Integer displayOrder;

    private Long categoryId;
    private String categoryName;

    // return image as Base64
    private String iconBase64;
}