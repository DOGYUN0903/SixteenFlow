package com.newspeed.sixteenflow.domain.like.controller;

import com.newspeed.sixteenflow.domain.like.dto.CommentLikeResponseDto;
import com.newspeed.sixteenflow.domain.like.dto.CommentLikeSearchDto.CommentLikeSearchDetailDto;
import com.newspeed.sixteenflow.domain.like.dto.CommentLikeSearchDto.CommentLikeSearchResponseDto;
import com.newspeed.sixteenflow.domain.like.service.CommentLikeService;
import com.newspeed.sixteenflow.global.common.ApiResponse;
import com.newspeed.sixteenflow.global.response.success.LikeSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentLikeController {

    //비지니스 로직을 처리할 서비스 의존성 주입
    private final CommentLikeService commentLikeService;
    //기능

    /**
     * 댓글 좋아요 토글 (좋아요/좋아요 취소)
     *
     * @param commentId          댓글 ID (PathVariable로 URL 경로에서 추출)
     * @param memberId  로그인한 사용자 ID (JWT에서 추출)
     * @return 좋아요 상태(true/false)와 좋아요 수를 담은 응답
     */
    //좋아요기능
    @PostMapping("/{commentId}/likes")
    public ResponseEntity<ApiResponse<CommentLikeResponseDto>> toggleLike(
            @PathVariable Long commentId,
            @AuthenticationPrincipal Long memberId
    ) {
        CommentLikeResponseDto commentResponse = commentLikeService.toggleLike(
                memberId, commentId);
        if (commentResponse.isLike()) { //like가 true라면
            return ApiResponse.status(LikeSuccess.COMMENT_LIKE_SUCCESS).body(commentResponse);
        } else { //false라면
            return ApiResponse.status(LikeSuccess.COMMENT_LIKE_CANCEL).body(commentResponse);
        }
    }

    /**
     * 댓글 좋아요를 누른 사용자 목록을 10개씩 조회
     *
     * @param commentId 조회할 댓글의 ID
     * @param pageable  페이지 정보 (page, size 등은 Spring이 자동으로 처리)
     * @return 좋아요 누른 사용자 리스트 (프로필 이미지, 닉네임 등)
     */
    @GetMapping("/{commentId}/likes") //GET /comments/1/likes?page=0&size=10
    public ResponseEntity<ApiResponse<CommentLikeSearchResponseDto>> getLikedMembers(
            @PathVariable Long commentId,
            Pageable pageable
    ) {
        // 서비스 호출 -> 좋아요 누른 사용자 목록 조회
        return ApiResponse.status(LikeSuccess.COMMENT_LIKE_LIST_SEARCH_SUCCESS)
                .body(commentLikeService.getLikedMembersByComment(commentId, pageable));
    }
}
