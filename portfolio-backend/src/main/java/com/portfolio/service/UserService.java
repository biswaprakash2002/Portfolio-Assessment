package com.portfolio.service;

import java.util.List;

import com.portfolio.dto.request.AssignRoleDto;
import com.portfolio.dto.request.UserLoginDto;
import com.portfolio.dto.request.UserRegisterDto;
import com.portfolio.dto.request.UserUpdateProfileDto;
import com.portfolio.dto.response.UserListDto;
import com.portfolio.dto.response.UserResponseDto;
import com.portfolio.exception.PortfolioException;



public interface UserService {

    // ===== User Registration & Auth =====
    UserResponseDto registerUser(UserRegisterDto dto, String role) throws PortfolioException;
    UserResponseDto login(UserLoginDto dto) throws PortfolioException;

    // ===== User Read =====
    UserResponseDto getUserById(Long userId) throws PortfolioException;
    UserResponseDto getUserByUsername(String username) throws PortfolioException;
    List<UserListDto> getAllUsers();

    // ===== User Update =====
    UserResponseDto updateUserProfile(String username, UserUpdateProfileDto dto) throws PortfolioException;
    void changePassword(String username, String oldPassword, String newPassword) throws PortfolioException;

    // ===== Account Status =====
    void verifyEmail(String username) throws PortfolioException;
    void lockUser(String username) throws PortfolioException;
    void unlockUser(String username) throws PortfolioException;
    void deactivateUser(String username) throws PortfolioException;

    // ===== Role Management =====
    void assignRoleToUser(AssignRoleDto dto) throws PortfolioException;
    void removeRoleFromUser(String username, Long roleId) throws PortfolioException;
    List<String> getUserRoles(String username) throws PortfolioException;

    // ===== Soft Delete =====
    void softDeleteUser(String username) throws PortfolioException;
}
