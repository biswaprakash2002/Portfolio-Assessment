package com.portfolio.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.portfolio.dto.Status;

import lombok.Data;

@Data
public class UserAuthLoginResponseDto {

    private Long id;
    private String username;
    private String email;
    private String token;
    private String role;
}