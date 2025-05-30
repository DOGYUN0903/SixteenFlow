package com.newspeed.sixteenflow.global.response.success;

import com.newspeed.sixteenflow.global.common.BaseCode;
import org.springframework.http.HttpStatus;

public enum CommentSucceess implements BaseCode {
    COMMENT_CREATED(HttpStatus.CREATED, "댓글이 생성되었습니다.."),
    COMMENT_READ_ALL(HttpStatus.OK, "댓글 목록을 전부 조회했습니다."),
    COMMENT_READ_ONE(HttpStatus.OK, "댓글 내용을 조회했습니다."),
    COMMENT_UPDATED(HttpStatus.OK, "댓글이 수정되었습니다."),
    COMMENT_DELETED(HttpStatus.OK, "댓글이 삭제되었습니다.");


    private HttpStatus status;
    private String message;

    CommentSucceess(HttpStatus status, String message) {
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
