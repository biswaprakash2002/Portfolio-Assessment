package com.portfolio;

import com.portfolio.service.Impl.UserServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PortfolioApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioApplication.class, args);
    }
    
    @Autowired
    UserServiceImp userServiceImp;

    @Bean
    CommandLineRunner run(UserServiceImp userServiceImp) {
        return args -> {
        	// call method hear
        	
        };
    }
}
