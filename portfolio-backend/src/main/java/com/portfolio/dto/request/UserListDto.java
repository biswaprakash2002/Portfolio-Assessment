package com.portfolio.dto.request;

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
    private String email;
    private Status accountStatus;
}
