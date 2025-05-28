package com.newspeed.sixteenflow.domain.member.controller;

import com.newspeed.sixteenflow.domain.member.dto.MemberRequestDto;
import com.newspeed.sixteenflow.domain.member.dto.MemberResponseDto;
import com.newspeed.sixteenflow.domain.member.service.MemberService;
import com.newspeed.sixteenflow.global.common.ApiResponse;
import com.newspeed.sixteenflow.global.response.success.MemberSuccess;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<ApiResponse<MemberResponseDto>> createMember(@Valid @RequestBody MemberRequestDto requestDto) {
        return ApiResponse.status(MemberSuccess.MEMBER_SIGNUP).body(memberService.createMember(requestDto));
    }



}
