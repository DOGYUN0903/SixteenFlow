package com.newspeed.sixteenflow.domain.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newspeed.sixteenflow.global.exception.BaseException;
import com.newspeed.sixteenflow.global.exception.member.MemberException;
import com.newspeed.sixteenflow.global.response.error.MemberError;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
            String header = request.getHeader("Authorization");

            if (header == null || !header.startsWith("Bearer ")) {
                throw new MemberException(MemberError.MEMBER_TOKEN_MALFORMED);
            }

            String token = header.substring(7);

            if (jwtUtil.validateToken(token)) {
                Long memberId = jwtUtil.getMemberIdFromToken(token);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(memberId, null, Collections.emptyList());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            chain.doFilter(request, response);

        } catch (BaseException e) {
            Map<String, String> errors = new LinkedHashMap<>();
            errors.put("statusCode", String.valueOf(e.getErrorCode().getStatus().value()));
            errors.put("message", e.getErrorCode().getMessage());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(errors));
            response.setStatus(e.getErrorCode().getStatus().value());
        }
    }
}
