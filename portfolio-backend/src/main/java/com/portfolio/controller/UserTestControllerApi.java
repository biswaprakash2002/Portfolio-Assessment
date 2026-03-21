//package com.portfolio.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.portfolio.dto.request.UserRegisterDto;
//import com.portfolio.dto.request.UserUpdateProfileDto;
//import com.portfolio.dto.response.UserResponseDto;
//import com.portfolio.exception.PortfolioException;
//import com.portfolio.service.UserService;
//
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("/users")
//public class UserTestControllerApi {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserTestControllerApi(UserService userService) {
//        this.userService = userService;
//    }
//    
// // ✅ GET: Get User by id
//    @GetMapping("/get/{userId}")
//    public String getUserById(
////            @RequestBody UserRegisterDto dto,
//    		@PathVariable Long userId
//            ) throws PortfolioException  {
//    	UserResponseDto dto=userService.getUserById(userId);
//        return "API is working 🚀"
//            +dto.toString()
//            ;
////        return ResponseEntity.ok(dto).toString();
//    }
//    
//    // ✅ GET: Get User by username
////    @GetMapping("/get/{username}")
////    public String getUserByUsername(
//////            @RequestBody UserRegisterDto dto,
////    		@PathVariable String username
////            ) throws PortfolioException  {
////    	UserResponseDto dto=userService.getUserByUsername(username);
////        return "API is working 🚀"
////            +dto.toString()
////            ;
//////        return ResponseEntity.ok(dto).toString();
////    }
////    
// // ✅ POST: Register User
//    @PostMapping("/register")
//    public ResponseEntity<UserResponseDto> registerUser(
//            @RequestBody UserRegisterDto dto) throws PortfolioException {
//
//        String role = "CLIENT"; // or "USER"
//
//        UserResponseDto response = userService.registerUser(dto, role);
//
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(response);
//    }
//    
// // ✅ PUT: Update User
//    @PutMapping("/update/{username}")
//    public UserResponseDto updateUser(@PathVariable String username,@RequestBody UserUpdateProfileDto updateDto) throws PortfolioException {
//    	UserResponseDto response = userService.updateUserProfile(username, updateDto);
//		return response;
//    }
//    
//}
