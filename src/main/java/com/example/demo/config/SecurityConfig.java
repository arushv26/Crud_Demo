package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//import java.io.IOException;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers(
                                HttpMethod.GET,
                                "/users/*/notes"
                        ).authenticated()

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/users/*/addNotes"
                        ).authenticated()

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/users/delete/**"
                        ).permitAll()
                .anyRequest().permitAll()
        )
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                )
                .httpBasic(httpBasic -> {});

        return httpSecurity.build();
    }
}
