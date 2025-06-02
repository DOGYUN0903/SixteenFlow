package com.newspeed.sixteenflow.domain.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newspeed.sixteenflow.global.response.error.MemberError;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put("statusCode", String.valueOf(MemberError.MEMBER_TOKEN_MALFORMED.getStatus().value()));
        errors.put("message", MemberError.MEMBER_TOKEN_MALFORMED.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(errors));
        response.setStatus(MemberError.MEMBER_TOKEN_MALFORMED.getStatus().value());
    }
}