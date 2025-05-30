package com.newspeed.sixteenflow.domain.comments.controller;


import com.newspeed.sixteenflow.domain.comments.dto.CommenReadListResponse;
import com.newspeed.sixteenflow.domain.comments.dto.UpdateCommentRequest;
import com.newspeed.sixteenflow.domain.comments.dto.CreateCommentRequest;
import com.newspeed.sixteenflow.domain.comments.dto.CommnetResponse;
import com.newspeed.sixteenflow.domain.comments.service.CommentService;
import com.newspeed.sixteenflow.global.common.ApiResponse;
import com.newspeed.sixteenflow.global.response.success.CommentSucceess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public  ResponseEntity<ApiResponse<List<CommenReadListResponse>>> findAllComments(
            @PathVariable Long postId,
    @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable){

        //pageable을 요청 변수로 받아줘야, 페이지가 넘겨짐
        Page <CommenReadListResponse> commentPageList= commentService.findAllComments(postId,pageable);

         //리스트로 변환하는 이유 : 좀 더 예쁘게, 필요한 것들만 출력하기 위함
        //페이지 이동 하기-> 매핑 주소 끝자리 부터 ?page={숫자}  ,페이지 숫자는 0부터 시작
        List<CommenReadListResponse> foundContent = commentPageList.getContent();

        return ApiResponse.status(CommentSucceess.COMMENT_READ_ALL).body(foundContent);
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
