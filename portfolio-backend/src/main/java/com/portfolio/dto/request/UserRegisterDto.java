package com.portfolio.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class UserRegisterDto {

    
//    private String username;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
    
    @NotBlank
    private String fullName;

    
}