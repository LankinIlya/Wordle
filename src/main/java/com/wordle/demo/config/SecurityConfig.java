package com.wordle.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security
        .authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config
        .annotation.web.builders.HttpSecurity;
import org.springframework.security.config
        .annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config
        .annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config
        .annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/**").permitAll())
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .logout(lOut -> {
                    lOut.logoutSuccessUrl("/")
                            .permitAll();
                })
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}