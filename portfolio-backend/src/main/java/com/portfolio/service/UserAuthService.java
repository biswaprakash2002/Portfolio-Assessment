package com.portfolio.service;


import com.portfolio.dto.request.UserAuthLoginRequestDto;
import com.portfolio.dto.request.UserRegisterDto;
import com.portfolio.dto.response.UserAuthLoginResponseDto;
import com.portfolio.dto.response.UserResponseDto;
import com.portfolio.exception.PortfolioException;



public interface UserAuthService {

    // ===== User Registration & Auth =====
	UserAuthLoginResponseDto registerUser(UserRegisterDto dto) throws PortfolioException;
	UserAuthLoginResponseDto login(UserAuthLoginRequestDto dto) throws PortfolioException;

}
