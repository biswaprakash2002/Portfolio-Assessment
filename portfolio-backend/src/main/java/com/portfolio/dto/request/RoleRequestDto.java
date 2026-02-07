package com.portfolio.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleRequestDto {

    @NotBlank
    @Size(max = 50)
    private String roleName;

    @Size(max = 250)
    private String description;

   
}