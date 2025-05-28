package com.newspeed.sixteenflow.domain.post.controller;

import com.newspeed.sixteenflow.domain.post.dto.PostListResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.PostResponseDto;
import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostRequestDto;
import com.newspeed.sixteenflow.domain.post.dto.create.CreatePostResponseDto;
import com.newspeed.sixteenflow.domain.post.service.PostService;
import com.newspeed.sixteenflow.global.common.ApiResponse;
import com.newspeed.sixteenflow.global.response.success.PostSuccess;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ApiResponse<CreatePostResponseDto>> createPost(@Valid @RequestBody CreatePostRequestDto requestDto) {
        // TODO: 인증 방식 결정 후 로그인 유저의 memberId 주입 로직 추가 예정
        // ex) @AuthenticationPrincipal Long memberId (Spring Security 사용 시)
        return ApiResponse.status(PostSuccess.POST_CREATED)
                .body(postService.createPost(requestDto)); // FIXME: 현재는 memberId 미전달 상태
    }

    /**
     * 게시글 전체 조회
     */
    @GetMapping
    public ResponseEntity<ApiResponse<PostListResponseDto>> findAllPost() {
        return ApiResponse.status(PostSuccess.POST_FOUND)
                .body(postService.findAllPost());
    }

    /**
     * 게시글 단건 조회
     */
    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponseDto>> findPostById(@PathVariable("postId") Long postId) {
        return ApiResponse.status(PostSuccess.POST_FOUND)
                .body(postService.findPostById(postId));
    }
}
