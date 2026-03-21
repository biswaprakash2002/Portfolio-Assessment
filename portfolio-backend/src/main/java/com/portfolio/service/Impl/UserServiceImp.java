//package com.portfolio.service.Impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.portfolio.dto.request.AssignRoleDto;
//import com.portfolio.dto.request.UserLoginDto;
//import com.portfolio.dto.request.UserRegisterDto;
//import com.portfolio.dto.request.UserUpdateProfileDto;
//import com.portfolio.dto.response.UserListDto;
//import com.portfolio.dto.response.UserResponseDto;
//import com.portfolio.entity.RoleEntity;
//import com.portfolio.entity.UserEntity;
//import com.portfolio.entity.UserRoleEntity;
//import com.portfolio.exception.PortfolioException;
//import com.portfolio.repository.RoleRepository;
//import com.portfolio.repository.UserRepository;
//import com.portfolio.repository.UserRoleRepository;
//import com.portfolio.service.UserService;
//
//import jakarta.transaction.Transactional;
//
//@Service
//public class UserServiceImp implements UserService{
//	
//	private final AuthenticationManager authenticationManager;
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final UserRoleRepository userRoleRepository;
////    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public UserServiceImp(UserRepository userRepository,
//    		RoleRepository roleRepository,
//    		UserRoleRepository userRoleRepository,
//    		AuthenticationManager authenticationManager
////    		,PasswordEncoder passwordEncoder
//    		) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.userRoleRepository = userRoleRepository;
//        this.authenticationManager = authenticationManager;
////        this.passwordEncoder = passwordEncoder;
//    }
//
//
//	@Override
//	public UserResponseDto getUserById(Long userId) throws PortfolioException {
//		//  Fetch User
//        UserEntity user = userRepository.getUserById(userId);
//
//        if(user == null) {
//        	throw new PortfolioException("No user Found With the Id : "+userId);
//        }
//
//        // 4️⃣ Prepare Response
//        UserResponseDto response = new UserResponseDto();
//        response.setEmail(user.getEmail());
////        response.setUsername(user.getUsername());
//        response.setFullName(user.getFullName());
//
//        return response;
//	}
//
//	@Override
//	public UserResponseDto getUserByUsername(String username) throws PortfolioException {
//			//  Fetch User
//	        UserEntity user = userRepository.findByUsername(username)
//					.get();
//
//	        if(user == null) {
//	        	throw new PortfolioException("No user Found With the Username : "+username);
//	        }
//
//	        // 4️⃣ Prepare Response
//	        UserResponseDto response = new UserResponseDto();
//	        response.setEmail(user.getEmail());
////	        response.setUsername(user.getUsername());
//	        response.setFullName(user.getFullName());
//
//	        return response;
//	}
//
//	@Override
//	public List<UserListDto> getAllUsers() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	@Transactional
//	public UserResponseDto updateUserProfile(String username, UserUpdateProfileDto dto) throws PortfolioException {
//
//	    // 1️⃣ Fetch user safely
//	    UserEntity user = userRepository.findByUsername(username)
//	            .orElseThrow(() ->
//	                    new PortfolioException("User not found with username: " + username)
//	            );
//
//	    // 2️⃣ Update only non-null fields
//	    if (dto.getFullName() != null) {
//	        user.setFullName(dto.getFullName());
//	    }
//	    if (dto.getHeadline() != null) {
//	        user.setHeadline(dto.getHeadline());
//	    }
//	    if (dto.getBio() != null) {
//	        user.setBio(dto.getBio());
//	    }
//	    if (dto.getProfileImageUrl() != null) {
//	        user.setProfileImageUrl(dto.getProfileImageUrl());
//	    }
//	    if (dto.getContactNumber() != null) {
//	        user.setContactNumber(dto.getContactNumber());
//	    }
//	    if (dto.getLocation() != null) {
//	        user.setLocation(dto.getLocation());
//	    }
//
//	    // 3️⃣ Save updated user
//	    UserEntity updatedUser = userRepository.save(user);
//
//	    // 4️⃣ Map Entity → Response DTO
//	    return mapToUserResponseDto(updatedUser);
//	}
//
//
//	private UserResponseDto mapToUserResponseDto(UserEntity user) {
//
//    UserResponseDto dto = new UserResponseDto();
//    dto.setId(user.getId());
////    dto.setUsername(user.getUsername());
//    dto.setEmail(user.getEmail());
//    dto.setFullName(user.getFullName());
//    dto.setHeadline(user.getHeadline());
//    dto.setBio(user.getBio());
//    dto.setProfileImageUrl(user.getProfileImageUrl());
//    dto.setContactNumber(user.getContactNumber());
//    dto.setLocation(user.getLocation());
//    dto.setEmailVerified(user.getEmailVerified());
//    dto.setAccountStatus(user.getAccountStatus());
//    dto.setCreatedAt(user.getCreatedAt());
//    dto.setUpdatedAt(user.getUpdatedAt());
//
//    // Convert roles
////    dto.setRoles(
////        user.getRoles()
////            .stream()
////            .map(role -> role.getRoleName())
////            .toList()
////    );
//
//    return dto;
//	}
//
//
//	@Override
//	public void changePassword(String username, String oldPassword, String newPassword)
//	        throws PortfolioException {
//	    // TODO Auto-generated method stub
//	}
//
//	@Override
//	public void verifyEmail(String username)
//	        throws PortfolioException {
//	    // TODO Auto-generated method stub
//	}
//
//	@Override
//	public void lockUser(String username)
//	        throws PortfolioException {
//	    // TODO Auto-generated method stub
//	}
//
//	@Override
//	public void unlockUser(String username)
//	        throws PortfolioException {
//	    // TODO Auto-generated method stub
//	}
//
//	@Override
//	public void deactivateUser(String username)
//	        throws PortfolioException {
//	    // TODO Auto-generated method stub
//	}
//
//	@Override
//	public void assignRoleToUser(AssignRoleDto dto)
//	        throws PortfolioException {
//	    // TODO Auto-generated method stub
//	}
//
//	@Override
//	public void removeRoleFromUser(String username, Long roleId)
//	        throws PortfolioException {
//	    // TODO Auto-generated method stub
//	}
//
//	@Override
//	public List<String> getUserRoles(String username)
//	        throws PortfolioException {
//	    // TODO Auto-generated method stub
//	    return null;
//	}
//
//	@Override
//	public void softDeleteUser(String username)
//	        throws PortfolioException {
//	    // TODO Auto-generated method stub
//	}
//
//	
//}
