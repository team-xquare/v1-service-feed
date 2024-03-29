package com.xquare.v1servicefeed.configuration.security;

import com.xquare.v1servicefeed.user.role.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getHeader("Request-User-Id") == null ||
                request.getHeader("Request-User-Role") == null ||
                request.getHeader("Request-User-Authorities") == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String userId = request.getHeader("Request-User-Id");
        UserRole userRole = UserRole.valueOf(request.getHeader("Request-User-Role"));
        List<String> userAuthorities = List.of(request.getHeader("Request-User-Authorities").split(" "));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String userAuthority : userAuthorities) {
            authorities.add(new SimpleGrantedAuthority(userAuthority));
        }

        authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.name()));
        UserDetails userDetails = new User(userId, "", authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
