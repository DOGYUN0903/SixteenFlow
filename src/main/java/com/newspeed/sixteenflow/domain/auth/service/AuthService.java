package com.newspeed.sixteenflow.domain.auth.service;

import com.newspeed.sixteenflow.domain.auth.dto.LoginRequestDto;
import com.newspeed.sixteenflow.domain.auth.dto.LoginUserDto;
import com.newspeed.sixteenflow.domain.auth.jwt.JwtUtil;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import com.newspeed.sixteenflow.domain.member.service.MemberService;
import com.newspeed.sixteenflow.global.exception.member.MemberException;
import com.newspeed.sixteenflow.global.response.error.MemberError;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginUserDto login(LoginRequestDto requestDto) {
        Member foundMember = memberService.findByLoginEmailOrElseThrow(requestDto.getEmail());

        if (!passwordEncoder.matches(requestDto.getPassword(), foundMember.getPassword())) {
            throw new MemberException(MemberError.MEMBER_LOGIN_FAILED);
        }

        String jwtToken = jwtUtil.generateToken(foundMember.getId());
        return new LoginUserDto(jwtToken);
    }
}
