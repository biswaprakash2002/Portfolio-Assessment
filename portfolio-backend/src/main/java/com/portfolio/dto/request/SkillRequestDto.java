package com.portfolio.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SkillRequestDto {

//    @NotBlank(message = "Skill name is required")
    private String skillName;

    private String proficiencyLevel; // ENUM as String

    @Min(value = 0)
    @Max(value = 100)
    private Integer proficiencyPercentage;

    private Double experienceYears;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    private Integer displayOrder;

    // Base64 image string from frontend
//    private String iconBase64;
}