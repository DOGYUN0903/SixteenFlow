package com.newspeed.sixteenflow.domain.post.controller;

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
        Long memberId = 1L;

        return ApiResponse.status(PostSuccess.POST_CREATED)
                .body(postService.create(memberId, requestDto)); // FIXME: 현재는 memberId 미전달 상태
    }

    /**
     * 게시글 전체 조회
     */
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<PostResponseDto>>> findAll(
            @RequestParam(value = "feed", required = false, defaultValue = "false") Boolean feed,
            Pageable pageable) {
        Long memberId = 1L;
        PageResponse<PostResponseDto> responseDto;

        if (feed) {
            responseDto = postService.findFollowingFeeds(memberId, pageable);
        } else {
            responseDto = postService.findAll(pageable);
        }

        return ApiResponse.status(PostSuccess.POST_FOUND)
                .body(responseDto);
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
        Long memberId = 1L;

        return ApiResponse.status(PostSuccess.POST_UPDATED)
                .body(postService.update(memberId, postId, requestDto));
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable("postId") Long postId) {
        Long memberId = 1L;
        postService.delete(memberId, postId);
        return ApiResponse.status(PostSuccess.POST_DELETED).body();
    }
}
