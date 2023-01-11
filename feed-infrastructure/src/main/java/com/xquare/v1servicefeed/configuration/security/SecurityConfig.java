package com.xquare.v1servicefeed.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xquare.v1servicefeed.user.role.UserRole;
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
    
    private static final String STUDENT = "ROLE_" + UserRole.STU.name();
    private static final String SCHOOL = "ROLE_" + UserRole.SCH.name();
    private static final String DORMITORY = "ROLE_" + UserRole.DOR.name();

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors().and()
                .csrf().disable()
                .formLogin().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()

               /* .antMatchers(HttpMethod.POST, "/feeds").hasAnyRole(STUDENT, SCHOOL, DORMITORY)
                .antMatchers(HttpMethod.PATCH, "/feeds/{feed-uuid}").hasAnyRole(STUDENT, SCHOOL, DORMITORY)
                .antMatchers(HttpMethod.DELETE, "/feeds/{feed-uuid}").hasAnyRole(STUDENT, SCHOOL, DORMITORY)
                .antMatchers(HttpMethod.GET, "/feeds").hasAnyRole(STUDENT, SCHOOL, DORMITORY)

                .antMatchers(HttpMethod.GET, "/feeds/category").hasAnyRole(STUDENT, SCHOOL, DORMITORY)

                .antMatchers(HttpMethod.POST, "/feeds/comments").hasAnyRole(STUDENT, SCHOOL, DORMITORY)
                .antMatchers(HttpMethod.PUT, "/feeds/comments/{comment-uuid}").hasAnyRole(STUDENT, SCHOOL, DORMITORY)
                .antMatchers(HttpMethod.DELETE, "/feeds/comments/{comment-uuid}").hasAnyRole(STUDENT, SCHOOL, DORMITORY)
                .antMatchers(HttpMethod.GET, "/feeds/comments/{feed-uuid}").hasAnyRole(STUDENT, SCHOOL, DORMITORY)

                .antMatchers(HttpMethod.POST, "/feeds/likes/{feed-uuid}").hasAnyRole(STUDENT, SCHOOL, DORMITORY)
                .antMatchers(HttpMethod.DELETE, "/feeds/likes/{feed-like-uuid}").hasAnyRole(STUDENT, SCHOOL, DORMITORY)

                .antMatchers(HttpMethod.POST, "/feeds/images/{feed-uuid}").hasAnyRole(STUDENT, SCHOOL, DORMITORY)
                .antMatchers(HttpMethod.DELETE, "/feeds/images/{feed-uuid}").hasAnyRole(STUDENT, SCHOOL, DORMITORY)
                .antMatchers(HttpMethod.PATCH, "/feeds/images/{feed-uuid}").hasAnyRole(STUDENT, SCHOOL, DORMITORY)*/

                .anyRequest().permitAll()

                .and()
                .apply(new FilterConfig(objectMapper))

                .and()
                .build();
    }
}
