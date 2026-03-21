package com.portfolio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.Filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig {
	
	@Autowired
    private JwtAuthFilter jwtAuthFilter;

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
//            	.requestMatchers("/users/register").permitAll()  // For all GET, POST, PUT, DELETE, etc.
                .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
//                .requestMatchers(HttpMethod.GET, "/user/register").permitAll()
                .anyRequest().authenticated()
//                .anyRequest().permitAll()
                
            )
            .addFilterBefore(jwtAuthFilter,
                    UsernamePasswordAuthenticationFilter.class);
         // 🔑 THIS WAS MISSING
//            .httpBasic(Customizer.withDefaults());



        return http.build();
    }
    // 🔑 THIS IS THE FIX
//    @Bean
//    public AuthenticationManager authenticationManager(
//            AuthenticationConfiguration configuration
//    ) throws Exception {
//        return configuration.getAuthenticationManager();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
