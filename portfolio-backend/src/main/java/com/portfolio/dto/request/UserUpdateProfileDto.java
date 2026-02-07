package com.portfolio.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateProfileDto {

    private String fullName;
    private String headline;
    private String bio;
    private String profileImageUrl;
    private String contactNumber;
    private String location;
}
