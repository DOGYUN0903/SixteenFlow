package com.newspeed.sixteenflow.domain.post.controller;

import com.newspeed.sixteenflow.domain.post.dto.PostListResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.PostResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostRequestDto;
import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.update.UpdatePostRequestDto;
import com.newspeed.sixteenflow.domain.post.dto.update.UpdatePostResponseDto;
import com.newspeed.sixteenflow.domain.post.service.PostService;
import com.newspeed.sixteenflow.global.common.ApiResponse;
import com.newspeed.sixteenflow.global.common.PageResponse;
import com.newspeed.sixteenflow.global.response.success.PostSuccess;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * 게시글 생성
     */
    @PostMapping
    public ResponseEntity<ApiResponse<CreatePostResponseDto>> create(@Valid @RequestBody CreatePostRequestDto requestDto) {
        // TODO: 인증 방식 결정 후 로그인 유저의 memberId 주입 로직 추가 예정
        // ex) @AuthenticationPrincipal Long memberId (Spring Security 사용 시)
        return ApiResponse.status(PostSuccess.POST_CREATED)
                .body(postService.create(1L, requestDto)); // FIXME: 현재는 memberId 미전달 상태
    }

    /**
     * 게시글 전체 조회
     */
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<PostResponseDto>>> findAll(Pageable pageable) {
        return ApiResponse.status(PostSuccess.POST_FOUND)
                .body(postService.findAll(pageable));
    }
    /**
     * 게시글 단건 조회
     */
    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponseDto>> findById(@PathVariable("postId") Long postId) {
        return ApiResponse.status(PostSuccess.POST_FOUND)
                .body(postService.findById(postId));
    }

    /**
     * 게시글 수정
     */
    @PatchMapping("/{postId}")
    public ResponseEntity<ApiResponse<UpdatePostResponseDto>> update(@PathVariable("postId") Long postId,
                                                                         @Valid @RequestBody UpdatePostRequestDto requestDto) {
        return ApiResponse.status(PostSuccess.POST_UPDATED)
                .body(postService.update(6L, postId, requestDto));
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable("postId") Long postId) {
        postService.delete(6L, postId);
        return ApiResponse.status(PostSuccess.POST_DELETED).body();
    }

    /**
     * 뉴스피드 게시물 조회(팔로우 한 사람의 게시물만 조회)
     */
    @GetMapping("/feed")
    public ResponseEntity<ApiResponse<PostListResponseDto>> getFollowingFeeds() {
        // TODO: 실제 로그인 ID 주입 필요
        Long memberId = 1L;
        return ApiResponse.status(PostSuccess.POST_FOUND)
                .body(postService.getFollowingFeeds(memberId));
    }
}
