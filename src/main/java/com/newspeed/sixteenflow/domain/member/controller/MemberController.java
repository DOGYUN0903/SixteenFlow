package com.newspeed.sixteenflow.domain.member.controller;

import com.newspeed.sixteenflow.domain.member.dto.*;
import com.newspeed.sixteenflow.domain.member.service.MemberService;
import com.newspeed.sixteenflow.global.common.ApiResponse;
import com.newspeed.sixteenflow.global.response.success.MemberSuccess;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<ApiResponse<MemberResponseDto>> create(@Valid @RequestBody MemberRequestDto requestDto) {
        return ApiResponse.status(MemberSuccess.MEMBER_SIGNUP).body(memberService.create(requestDto));
    }

    //todo: 로그인, 로그아웃

    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResponse<MemberResponseDto>> findById(@PathVariable Long memberId) {
        return ApiResponse.status(MemberSuccess.MEMBER_FOUND).body(memberService.findById(memberId));
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<ApiResponse<MemberResponseDto>> updateProfile(@PathVariable Long memberId, @Valid @RequestBody MemberUpdateRequestDto updateDto) {
        return ApiResponse.status(MemberSuccess.MEMBER_UPDATE_PROFILE).body(memberService.update(memberId, updateDto));
    }

    @PatchMapping("/{memberId}/password")
    public ResponseEntity<ApiResponse<Void>> changePassword(@PathVariable Long memberId, @Valid @RequestBody ChangePasswordRequestDto passwordDto) {
        memberService.changePassword(memberId, passwordDto);
        return ApiResponse.status(MemberSuccess.MEMBER_UPDATE_PASSWORD).body();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long memberId, @Valid @RequestBody MemberDeleteRequestDto deleteDto) {
        memberService.delete(memberId, deleteDto);
        return ApiResponse.status(MemberSuccess.MEMBER_WITHDRAW).body();
    }
}