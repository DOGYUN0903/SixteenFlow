package com.newspeed.sixteenflow.global.response.error;

import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.newspeed.sixteenflow.global.common.BaseCode;
import org.springframework.http.HttpStatus;

public enum CommentError implements BaseCode {

    //  생성 메세지
    COMMENT_POST_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 작성할 게시글이 존재하지 않습니다."),
    COMMENT_MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글 작성자 정보가 존재하지 않습니다."),
    COMMENT_CONTENT_EMPTY(HttpStatus.BAD_REQUEST, "댓글 내용을 비워둘 수 없습니다."),

    // 조회
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글 내용은 비워둘 수 없습니다."),

    //수정
    COMMENT_UPDATE_UNAUTHORIZED(HttpStatus.FORBIDDEN, "해당 댓글을 찾을 수 없습니다."),
    COMMENT_UPDATE_NOT_FOUND(HttpStatus.NOT_FOUND, "수정하려는 댓글이 존재하지 않습니다."),
    COMMENT_UPDATE_COMMENT_EMPTY(HttpStatus.BAD_REQUEST, "댓글 내용을 비운 채로 수정할 수 없습니다."),

    //삭제
    COMMENT_DELETE_NOT_FOUND(HttpStatus.NOT_FOUND, "삭제할 댓글이 존재하지 않습니다."),
    COMMENT_DELETE_UNAUTHORIZED(HttpStatus.FORBIDDEN, "본인이 작성한 댓글만 삭제할 수 있습니다.");


    private HttpStatus status;
    private String message;

    CommentError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
