package com.portfolio.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SkillCategoryRequestDto {

//    @NotBlank(message = "Category name is required")
    private String name;

    private String description;
}