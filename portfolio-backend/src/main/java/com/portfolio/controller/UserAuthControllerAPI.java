package com.portfolio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.portfolio.dto.request.UserAuthLoginRequestDto;
import com.portfolio.dto.request.UserRegisterDto;
import com.portfolio.dto.response.UserAuthLoginResponseDto;
import com.portfolio.exception.PortfolioException;
import com.portfolio.service.Impl.UserAuthServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserAuthControllerAPI {

    private final UserAuthServiceImp userAuthService;

    // ✅ Constructor Injection (No @Autowired needed)
    public UserAuthControllerAPI(UserAuthServiceImp userAuthService) {
        this.userAuthService = userAuthService;
    }

    // ✅ POST: Register User
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDto dto) throws PortfolioException {

        UserAuthLoginResponseDto response = userAuthService.registerUser(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserAuthLoginRequestDto dto) throws PortfolioException {
        UserAuthLoginResponseDto response = userAuthService.login(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    
}