package com.newspeed.sixteenflow.domain.comments.controller;


import com.newspeed.sixteenflow.domain.comments.dto.CommentResponse;
import com.newspeed.sixteenflow.domain.comments.dto.UpdateCommentRequest;
import com.newspeed.sixteenflow.domain.comments.dto.CreateCommentRequest;
import com.newspeed.sixteenflow.domain.comments.service.CommentService;
import com.newspeed.sixteenflow.global.common.ApiResponse;
import com.newspeed.sixteenflow.global.common.PageResponse;
import com.newspeed.sixteenflow.global.response.success.CommentSucceess;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    //속성
    private final CommentService commentService;

    //생성자
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //기능
    /**
     * 댓글 생성
     */
    @PostMapping("/posts/{postId}/comments")
    public  ResponseEntity<ApiResponse<CommentResponse>>  createComment(
            @PathVariable Long postId,
            @Validated  @RequestBody CreateCommentRequest createRequest
            ){
        Long memberId=1L;
        CommentResponse createResponse = commentService.createComment(postId, memberId, createRequest);
        return ApiResponse.status(CommentSucceess.COMMENT_CREATED).body(createResponse);
    }

    /**
     * 한 페이지 내 댓글 전체 조회
     */
    @GetMapping("/posts/{postId}/comments")
    public  ResponseEntity<ApiResponse<PageResponse<CommentResponse>>> findAllComments(
            @PathVariable Long postId,
    @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable){
        //pageable을 요청 변수로 받아줘야, 페이지가 넘겨짐
        PageResponse<CommentResponse> pagingAllComments = commentService.findAllComments(postId, pageable);
        return ApiResponse.status(CommentSucceess.COMMENT_READ_ALL).body(pagingAllComments);
    }

    /**
     * 댓글 단 건 조회
     */
    @GetMapping("/comments/{id}")
    public ResponseEntity<ApiResponse<CommentResponse>> findComment(@PathVariable Long id){
        CommentResponse foundComment = commentService.findComment(id);
        return ApiResponse.status(CommentSucceess.COMMENT_READ_ONE).body(foundComment);
    }

    /**
     * 댓글 수정
     */
    @PatchMapping("/comments/{id}")
    public  ResponseEntity<ApiResponse <CommentResponse>> updateComment(
            @PathVariable Long id,
            @Validated @RequestBody UpdateCommentRequest updateRequest
            ){
        Long memberId= 1L;
        CommentResponse updatedOne = commentService.updateComment(id, memberId, updateRequest);
        return ApiResponse.status(CommentSucceess.COMMENT_UPDATED).body(updatedOne);
    }

    /**
     * 댓글 삭제
     * 반환 값 어떻게 해야 하는지 질문
     */
    @DeleteMapping("/comments/{id}") // 로그인 상태일 때를 인식해야 함
    public ResponseEntity<ApiResponse<String>>  deleteComment(@PathVariable Long id){
        Long memberId= 1L;
        commentService.deleteComment(memberId, id);
        return ApiResponse.status(CommentSucceess.COMMENT_DELETED).body();
    }
}
