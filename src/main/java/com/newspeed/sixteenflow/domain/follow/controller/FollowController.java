package com.newspeed.sixteenflow.domain.follow.controller;

import com.newspeed.sixteenflow.domain.follow.service.FollowService;
import com.newspeed.sixteenflow.global.common.ApiResponse;
import com.newspeed.sixteenflow.global.response.success.FollowSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @GetMapping("/members/{memberId}/followings")
    public ResponseEntity<ApiResponse<List<Long>>> getFollowings(
            @PathVariable Long memberId
    ){
        throw new RuntimeException();
    }
}
