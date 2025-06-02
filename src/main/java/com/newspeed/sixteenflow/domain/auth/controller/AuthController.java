package com.newspeed.sixteenflow.domain.auth.controller;

import com.newspeed.sixteenflow.domain.auth.dto.LoginRequestDto;
import com.newspeed.sixteenflow.domain.auth.dto.LoginUserDto;
import com.newspeed.sixteenflow.domain.auth.service.AuthService;
import com.newspeed.sixteenflow.global.common.ApiResponse;
import com.newspeed.sixteenflow.global.response.success.MemberSuccess;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginUserDto>> login(@Valid @RequestBody LoginRequestDto requestDto) {
        return ApiResponse.status(MemberSuccess.MEMBER_LOGIN).body(authService.login(requestDto));
    }
}