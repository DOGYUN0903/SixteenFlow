package com.newspeed.sixteenflow.domain.follow.controller;

import com.newspeed.sixteenflow.domain.follow.dto.FollowRequestDto;
import com.newspeed.sixteenflow.domain.follow.dto.FollowResponseDto;
import com.newspeed.sixteenflow.domain.follow.service.FollowService;
import com.newspeed.sixteenflow.global.common.ApiResponse;
import com.newspeed.sixteenflow.global.response.success.FollowSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/members/{memberId}/follow")
    public ResponseEntity<ApiResponse<FollowResponseDto>> follow(
            @PathVariable Long memberId,
            @Validated @RequestBody FollowRequestDto dto
    ){
        FollowResponseDto response = followService.follow(memberId, dto.getMemberId());
        return ApiResponse.status(FollowSuccess.FOLLOW_SUCCESS).body(response);
    }

    @DeleteMapping("/members/{memberId}/unfollow")
    public ResponseEntity<ApiResponse<FollowResponseDto>> unfollow(
            @PathVariable Long memberId,
            @Validated @RequestBody FollowRequestDto dto
    ){
        FollowResponseDto response = followService.unfollow(memberId, dto.getMemberId());
        return ApiResponse.status(FollowSuccess.UNFOLLOW_SUCCESS).body(response);
    }
}
