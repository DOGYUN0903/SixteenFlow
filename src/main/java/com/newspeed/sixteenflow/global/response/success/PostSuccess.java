package com.newspeed.sixteenflow.global.response.success;

import com.newspeed.sixteenflow.global.common.BaseCode;
import org.springframework.http.HttpStatus;

public enum PostSuccess implements BaseCode {
    POST_CREATED(HttpStatus.CREATED, "게시글이 생성되었습니다."),
    POST_FOUND(HttpStatus.OK, "게시글이 조회되었습니다"),
    POST_UPDATED(HttpStatus.OK, "게시글이 수정되었습니다.");

    private HttpStatus status;
    private String message;

    PostSuccess(HttpStatus status, String message) {
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
