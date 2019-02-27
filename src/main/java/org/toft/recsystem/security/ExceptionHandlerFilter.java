package org.toft.recsystem.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.toft.recsystem.domain.ApiError;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
    final private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (JWTVerificationException | AuthenticationException e) {
            ApiError error = new ApiError(HttpStatus.UNAUTHORIZED, e.getLocalizedMessage());
            String json = objectMapper.writeValueAsString(error);

            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            System.out.println(httpServletRequest.getServletPath());
            if (!Objects.equals(httpServletRequest.getServletPath(), SecurityConstants.LOGIN_URL))
                httpServletResponse.setHeader(HttpHeaders.WWW_AUTHENTICATE, "Bearer");

            httpServletResponse.getWriter().write(json);
        }
    }
}
