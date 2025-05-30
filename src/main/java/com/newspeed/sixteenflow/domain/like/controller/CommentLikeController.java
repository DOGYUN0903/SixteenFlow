package com.newspeed.sixteenflow.domain.like.controller;

import com.newspeed.sixteenflow.domain.like.dto.CommentLikeRequestDto;
import com.newspeed.sixteenflow.domain.like.dto.CommentLikeResponseDto;
import com.newspeed.sixteenflow.domain.like.dto.CommentLikeSearchDto.CommentLikeSearchListResponseDto;
import com.newspeed.sixteenflow.domain.like.dto.PostLikeSearchDto.PostLikeSearchListResponseDto;
import com.newspeed.sixteenflow.domain.like.service.CommentLikeService;
import com.newspeed.sixteenflow.global.common.ApiResponse;
import com.newspeed.sixteenflow.global.response.success.LikeSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
     * @param commentId 댓글 ID (PathVariable로 URL 경로에서 추출)
     * @param commentLikeRequest 요청 Body에 담긴 memberId
     * @return 좋아요 상태(true/false)와 좋아요 수를 담은 응답
     */
    //좋아요기능
    @PostMapping("/{commentId}/likes")
    public ResponseEntity<ApiResponse<CommentLikeResponseDto>> toggleLike(
            @PathVariable Long commentId,
            @RequestBody CommentLikeRequestDto commentLikeRequest
    ){
        CommentLikeResponseDto commentResponse = commentLikeService.toggleLike(
                commentLikeRequest.getMemberId(),commentId);
        if(commentResponse.isLike()){ //like가 true라면
            return ApiResponse.status(LikeSuccess.COMMENT_LIKE_SUCCESS).body(commentResponse);
        } else { //false라면
            return ApiResponse.status(LikeSuccess.COMMENT_LIKE_CANCEL).body(commentResponse);
        }
    }

    @GetMapping("/{commentId}/likes")
    public ResponseEntity<ApiResponse<CommentLikeSearchListResponseDto>> getLikedMembers(
            @PathVariable Long commentId){
        CommentLikeSearchListResponseDto responseDto = commentLikeService.getLikedMembersByComment(commentId);
        return ApiResponse.status(LikeSuccess.COMMENT_LIKE_LIST_SEARCH_SUCCESS).body(responseDto);
    }
}
