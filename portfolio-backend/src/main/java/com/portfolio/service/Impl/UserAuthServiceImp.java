package com.portfolio.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.portfolio.config.JwtUtil;
import com.portfolio.dto.Status;
import com.portfolio.dto.request.UserAuthLoginRequestDto;
import com.portfolio.dto.request.UserRegisterDto;
import com.portfolio.dto.response.UserAuthLoginResponseDto;
import com.portfolio.entity.RoleEntity;
import com.portfolio.entity.UserEntity;
import com.portfolio.entity.UserRoleEntity;
import com.portfolio.exception.PortfolioException;
import com.portfolio.repository.RoleRepository;
import com.portfolio.repository.UserRepository;
import com.portfolio.repository.UserRoleRepository;
import com.portfolio.service.UserAuthService;

import jakarta.transaction.Transactional;

@Service
public class UserAuthServiceImp implements UserAuthService{

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final UserRoleRepository userRoleRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	@Autowired
	public UserAuthServiceImp(UserRepository userRepository, RoleRepository roleRepository,
			UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder,JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userRoleRepository = userRoleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil=jwtUtil;
	}
	@Override
	public UserAuthLoginResponseDto registerUser(UserRegisterDto dto) throws PortfolioException {

		// 1️⃣ Get Role
		RoleEntity role = roleRepository.findByRoleName("CLIENT")
				.orElseThrow(() -> new PortfolioException("Role not found: CLIENT"));

		// First user = ADMIN
		if (userRepository.count() == 0) {
			role = roleRepository.findByRoleName("ADMIN")
					.orElseThrow(() -> new PortfolioException("Role not found: ADMIN"));
		}

		// 2️⃣ Check email exists
		if (userRepository.existsByEmail(dto.getEmail())) {
			throw new PortfolioException("User.USER_EMAIL_ALREADY_EXIST");
		}

		// 3️⃣ Save User
		UserEntity newUser = new UserEntity();
		newUser.setEmail(dto.getEmail());
		newUser.setFullName(dto.getFullName());
		newUser.setPasswordHash(passwordEncoder.encode(dto.getPassword())); // ✅ FIX

		userRepository.save(newUser);

		// 4️⃣ Assign Role
		UserRoleEntity userRole = new UserRoleEntity();
		userRole.setUser(newUser);
		userRole.setRole(role);

		userRoleRepository.save(userRole);

		// 5️⃣ Response
		UserAuthLoginResponseDto response = new UserAuthLoginResponseDto();
		response.setEmail(newUser.getEmail());
		response.setUsername(newUser.getFullName());

		return response;
	}
	
	@Override
	@Transactional
	public UserAuthLoginResponseDto login(UserAuthLoginRequestDto dto) throws PortfolioException {

	    // 1️⃣ Find user
	    UserEntity user = userRepository.findByEmail(dto.getEmail())
	            .orElseThrow(() -> new PortfolioException("User.NOT_FOUND"));

	    // 2️⃣ Validate password
	    if (!passwordEncoder.matches(dto.getPassword(), user.getPasswordHash())) {
	        throw new PortfolioException("User.Login.WRONG_CREDENTIAL");
	    }

	    // 3️⃣ Check deleted
	    if (Boolean.TRUE.equals(user.getIsDeleted())) {
	        throw new PortfolioException("User.Login.RIGHT_CREDENTIAL_ACCOUNT_DELETED");
	    }

	    // 4️⃣ Check account status
	    if (user.getAccountStatus() == Status.INACTIVE || user.getAccountStatus() == Status.SUSPENDED) {
	        throw new PortfolioException(
	                "User.Login.RIGHT_CREDENTIAL_ACCOUNT_" + user.getAccountStatus().name()
	        );
	    }

//	    // 5️⃣ Get ALL roles (IMPORTANT 🔥)
//	    List<UserRoleEntity> userRoles = userRoleRepository.findAllByUser(user);
	    
	    // 5️⃣ Get FIRST role only
	    UserRoleEntity userRole = userRoleRepository.findAllByUser(user)
	            .stream()
	            .findFirst()
	            .orElseThrow(() -> new PortfolioException("User.ROLE_NOT_ASSIGNED"));


//	    if (userRoles.isEmpty()) {
//	        throw new PortfolioException("User role not assigned");
//	    }
//
//	    List<String> roles = userRoles.stream()
//	            .map(ur -> ur.getRole().getRoleName())
//	            .toList();
	    
	    String role = userRole.getRole().getRoleName();  // add

	    // 6️⃣ Generate JWT
	    String token = jwtUtil.generateToken(user.getEmail(), role);  // roles

	    // 7️⃣ Response
	    UserAuthLoginResponseDto response = new UserAuthLoginResponseDto();
	    response.setEmail(user.getEmail());
	    response.setUsername(user.getFullName());
	    response.setRole(role);   // ✅ use List<String>  //  roles // setRoles()
	    response.setToken(token);

	    return response;
	}

}