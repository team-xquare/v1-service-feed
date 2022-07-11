package com.xquare.v1servicefeed.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xquare.v1servicefeed.configuration.role.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final ObjectMapper objectMapper;

    private static final String STUDENT = UserRole.STU.name();
    private static final String SCHOOL = UserRole.SCH.name();
    private static final String DORMITORY = UserRole.DOR.name();


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors().and()
                .csrf().disable()
                .formLogin().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()

                .antMatchers(HttpMethod.POST, "/feeds").hasAnyRole(STUDENT, SCHOOL, DORMITORY)

                .antMatchers(HttpMethod.POST, "/comments").hasAnyRole(STUDENT, SCHOOL, DORMITORY)
                .anyRequest().authenticated()

                .and()
                .apply(new FilterConfig(objectMapper))

                .and()
                .build();
    }
}
