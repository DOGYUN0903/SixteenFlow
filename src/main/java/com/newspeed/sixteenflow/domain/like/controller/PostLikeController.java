package com.newspeed.sixteenflow.domain.like.controller;

import com.newspeed.sixteenflow.domain.like.dto.PostLikeRequestDto;
import com.newspeed.sixteenflow.domain.like.dto.PostLikeResponseDto;
import com.newspeed.sixteenflow.domain.like.dto.PostLikeSearchDto.PostLikeSearchListResponseDto;
import com.newspeed.sixteenflow.domain.like.service.PostLikeService;
import com.newspeed.sixteenflow.global.common.ApiResponse;
import com.newspeed.sixteenflow.global.response.success.LikeSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostLikeController {

    // 비즈니스 로직을 처리할 서비스 의존성 주입
    private final PostLikeService postLikeService;

    /**
     * 게시글 좋아요 토글 (좋아요/좋아요 취소)
     * @param postId 게시글 ID (PathVariable로 URL 경로에서 추출)
     * @param postLikeRequest 요청 Body에 담긴 memberId
     * @return 좋아요 상태(true/false)와 좋아요 수를 담은 응답
     */

    //좋아요기능
    @PostMapping("/{postId}/likes")  // POST 요청 처리: 예) /posts/1/likes
    public ResponseEntity<ApiResponse<PostLikeResponseDto>> toggleLike(
            @PathVariable Long postId, // 경로에서 postId 추출
            @RequestBody PostLikeRequestDto postLikeRequest // JSON Body에서 memberId 추출
    ) {
        // 좋아요 처리 (토글 방식): 있으면 취소, 없으면 생성
        PostLikeResponseDto postresponse = postLikeService.toggleLike(postLikeRequest.getMemberId(), postId);

        // 좋아요 성공 또는 취소 여부에 따라 응답 메시지 구분 (if문 사용)
        if (postresponse.isLike()) {
            return ApiResponse.status(LikeSuccess.POST_LIKE_SUCCESS).body(postresponse);

        } else {
            return ApiResponse.status(LikeSuccess.POST_LIKE_CANCEL).body(postresponse);
        }
    }

    /**
     * 특정 게시글의 좋아요한 회원 목록 조회
     * @param postId 게시글 ID
     * @return 좋아요 수 및 사용자 정보 목록을 포함한 응답
     */

    //좋아요 전체 조회기능
    @GetMapping("/{postId}/likes") // GET 요청 처리: 예) /posts/1/likes
    public ResponseEntity<ApiResponse<PostLikeSearchListResponseDto>> getLikedMembers(@PathVariable Long postId) {

        // 특정 게시글에 좋아요를 누른 사용자 목록 조회
        PostLikeSearchListResponseDto responseDto = postLikeService.getLikedMembersByPost(postId);

        // 응답 객체로 포장하여 반환
        return ApiResponse.status(LikeSuccess.POST_LIKE_LIST_SEARCH_SUCCESS).body(responseDto);
    }
}
