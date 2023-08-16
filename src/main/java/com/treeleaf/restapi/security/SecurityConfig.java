package com.treeleaf.restapi.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return  http
                .authorizeRequests()
                .requestMatchers("/login","/register")
                .permitAll()
                .requestMatchers(HttpMethod.DELETE,"/api/v1/**").hasRole(UserRole.ADMIN.name())
                .requestMatchers(HttpMethod.POST,"/api/v1/**").hasAnyRole(UserRole.ADMIN.name(),UserRole.NORMAL.name())
                .requestMatchers(HttpMethod.PUT,"/api/v1/**").hasRole(UserRole.ADMIN.name())
                .requestMatchers(HttpMethod.GET,"/api/v1/**").hasAnyRole(UserRole.ADMIN.name(),UserRole.NORMAL.name())
                .anyRequest()
                .authenticated()
                .and()
                .build();
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails adminUser = User.withUsername("Kailash@123")
                .password("Kailash123")
                .roles("ADMIN")
                .build();

        UserDetails normalUser = User.withUsername("Omkar@123")
                .password("omkar123")
                .roles("NORMAL")
                .build();

        return new InMemoryUserDetailsManager(normalUser,adminUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

}
