package com.portfolio.dto.response;

import java.time.LocalDateTime;

import com.portfolio.dto.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListDto {

    private Long id;

    private String username;
    private String fullName;
    private String email;

    private String profileImageUrl;
    private String contactNumber;
    private String location;

    private Boolean emailVerified;
    private Status accountStatus;

    private LocalDateTime createdAt;
}
