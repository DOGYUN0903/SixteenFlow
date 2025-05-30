package com.newspeed.sixteenflow.domain.like.controller;

import com.newspeed.sixteenflow.domain.like.dto.PostLikeRequestDto;
import com.newspeed.sixteenflow.domain.like.dto.PostLikeResponseDto;
import com.newspeed.sixteenflow.domain.like.service.PostLikeService;
import com.newspeed.sixteenflow.domain.post.entity.Post;
import com.newspeed.sixteenflow.global.common.ApiResponse;
import com.newspeed.sixteenflow.global.response.success.LikeSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostLikeController {

    private final PostLikeService postLikeService;

    //좋아요기능
    @PostMapping("/{postId}/likes")
    public ResponseEntity<ApiResponse<PostLikeResponseDto>> toggleLike(
            @PathVariable Long postId,
            @RequestBody PostLikeRequestDto postLikeRequest
    ){

        PostLikeResponseDto postresponse = postLikeService.toggleLike(postLikeRequest.getMemberId(), postId);

        if (postresponse.isLike()) {
            return ApiResponse.status(LikeSuccess.POST_LIKE_SUCCESS).body(postresponse);
        } else {
            return ApiResponse.status(LikeSuccess.POST_LIKE_CANCEL).body(postresponse);
        }
    }

    //좋아요 전체 조회기능 구현 필요
}
