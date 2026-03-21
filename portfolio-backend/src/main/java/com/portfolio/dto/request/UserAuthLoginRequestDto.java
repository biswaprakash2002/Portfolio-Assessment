package com.portfolio.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserAuthLoginRequestDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}