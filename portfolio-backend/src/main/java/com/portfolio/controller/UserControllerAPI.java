package com.portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserControllerAPI {
	
//	@Autowired
	@GetMapping("/hello")
	public ResponseEntity<String> Test(Authentication auth) {
		return ResponseEntity.ok("API WORK SAY HELLO     "+auth.getName());
		
	}

	
    
}
