package com.portfolio.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDto {

    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;

}