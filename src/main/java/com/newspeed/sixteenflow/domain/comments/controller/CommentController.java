package com.newspeed.sixteenflow.domain.comments.controller;


import com.newspeed.sixteenflow.domain.comments.dto.UpdateCommentRequest;
import com.newspeed.sixteenflow.domain.comments.dto.CreateCommentRequest;
import com.newspeed.sixteenflow.domain.comments.dto.CommnetResponse;
import com.newspeed.sixteenflow.domain.comments.service.CommentService;
import com.newspeed.sixteenflow.global.common.ApiResponse;
import com.newspeed.sixteenflow.global.response.success.CommentSucceess;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public  ResponseEntity<ApiResponse<CommnetResponse>>  createComment(
            @PathVariable Long postId,
            @Validated  @RequestBody CreateCommentRequest createRequest

            ){
        Long memberId=1L;
        CommnetResponse createResponse = commentService.createComment(postId, memberId, createRequest);
        return ApiResponse.status(CommentSucceess.COMMENT_CREATED).body(createResponse);
    }

    /**
     * 한 페이지 내 댓글 전체 조회
     */
    @GetMapping("/posts/{postId}/comments")
    public  ResponseEntity<ApiResponse<List<CommnetResponse>>> findAllComments(@PathVariable Long postId){
         List <CommnetResponse> foundCommentList= commentService.findAllComments(postId);//오타
        return ApiResponse.status(CommentSucceess.COMMENT_READ_ALL).body(foundCommentList);
    }

    /**
     * 댓글 단 건 조회
     */
    @GetMapping("/comments/{id}")
    public ResponseEntity<ApiResponse<CommnetResponse>> findComment(@PathVariable Long id){

        CommnetResponse foundComment = commentService.findComment(id);
        return ApiResponse.status(CommentSucceess.COMMENT_READ_ONE).body(foundComment);

    }

    /**
     * 댓글 수정
     */
    @PatchMapping("/comments/{id}")
    public  ResponseEntity<ApiResponse <CommnetResponse>> updateComment(
            @PathVariable Long id,
            @Validated @RequestBody UpdateCommentRequest updateRequest
            ){
        Long memberId= 1L;
        CommnetResponse updatedOne = commentService.updateComment(id, memberId, updateRequest);
        return ApiResponse.status(CommentSucceess.COMMENT_UPDATED).body(updatedOne);
    }

    /**
     * 댓글 삭제
     * 반환 값 어떻게 해야 하는지 질문
     */
    @DeleteMapping("/posts/{postId}/comments/{id}") // 로그인 상태일 때를 인식해야 함
    public ResponseEntity<ApiResponse<String>>  deleteComment(@PathVariable Long id){
        Long memberId= 1L;
        commentService.deleteComment(memberId, id);
        return ApiResponse.status(CommentSucceess.COMMENT_DELETED).body();
    }
}
