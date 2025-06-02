package com.newspeed.sixteenflow.domain.like.controller;

import com.newspeed.sixteenflow.domain.like.dto.PostLikeResponseDto;
import com.newspeed.sixteenflow.domain.like.dto.PostLikeSearchDto.PostLikeSearchDetailDto;
import com.newspeed.sixteenflow.domain.like.dto.PostLikeSearchDto.PostLikeSearchResponseDto;
import com.newspeed.sixteenflow.domain.like.service.PostLikeService;
import com.newspeed.sixteenflow.global.common.ApiResponse;
import com.newspeed.sixteenflow.global.common.PageResponse;
import com.newspeed.sixteenflow.global.response.success.LikeSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
     * @param memberId  로그인한 사용자 ID (JWT에서 추출)
     * @return 좋아요 상태(true/false)와 좋아요 수를 담은 응답
     */

    //좋아요기능
    @PostMapping("/{postId}/likes")  // POST 요청 처리: 예) /posts/1/likes
    public ResponseEntity<ApiResponse<PostLikeResponseDto>> toggleLike(
            @PathVariable Long postId, // 경로에서 postId 추출
            @AuthenticationPrincipal Long memberId
    ) {
        // 좋아요 처리 (토글 방식): 있으면 취소, 없으면 생성
        PostLikeResponseDto postresponse = postLikeService.toggleLike(memberId, postId);

        // 좋아요 성공 또는 취소 여부에 따라 응답 메시지 구분
        if (postresponse.isLike()) {  //좋아요 시
            return ApiResponse.status(LikeSuccess.POST_LIKE_SUCCESS).body(postresponse);

        } else { //좋아요 취소 시
            return ApiResponse.status(LikeSuccess.POST_LIKE_CANCEL).body(postresponse);
        }
    }


    /**
     * 게시글 좋아요를 누른 사용자 목록을 10개씩 조회
     * @param postId 조회할 게시글의 ID
     * @param pageable 페이지 정보
     * @return 좋아요 누른 사용자 리스트 (프로필 이미지, 닉네임)
     */
    @GetMapping("/{postId}/likes") // GET /posts/1/likes?page=0&size=10
    public ResponseEntity<ApiResponse<PostLikeSearchResponseDto>> getLikedMembers(
            @PathVariable Long postId,
            Pageable pageable
    ) {
        // 서비스 호출 -> 좋아요 누른 사용자 목록 조회
        return ApiResponse.status(LikeSuccess.POST_LIKE_LIST_SEARCH_SUCCESS)
                .body(postLikeService.getLikedMembersByPost(postId, pageable));
    }
}
