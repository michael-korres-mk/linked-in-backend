package com.licnosoft.linkedin.config.security;

import com.licnosoft.linkedin.config.exceptions.AppUnauthorizedException;
import com.licnosoft.linkedin.domain.users.User;
import com.licnosoft.linkedin.model.auth.UserInfoDTO;
import com.licnosoft.linkedin.services.users.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final UserService userService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Autowired
    public JwtTokenFilter(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String requestUUIDHeader = request.getHeader("UUID");

        if (requestTokenHeader == null) {
            chain.doFilter(request, response);
            return;
        }

        if(!requestTokenHeader.startsWith("Bearer ")) {
            resolver.resolveException(request, response, null, new AppUnauthorizedException());
            return;
        }

        UserInfoDTO dto = jwtUtil.decodeJwtToUserInfo(requestTokenHeader.substring(7));
        if (SecurityContextHolder.getContext().getAuthentication() == null) {

//            User user = userService.findByUsernameAndType(dto.getUsername(), UserType.STATE_EMPLOYEE);
            User user = userService.findEntityByUsername(dto.getUsername());

            UsernamePasswordAuthenticationToken authentication;
            authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}
