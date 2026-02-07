package com.portfolio.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssignRoleDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long roleId;

}