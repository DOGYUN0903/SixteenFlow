package com.newspeed.sixteenflow.global.response.error;

import com.newspeed.sixteenflow.global.common.BaseCode;
import org.springframework.http.HttpStatus;

public enum PostError implements BaseCode {

    POST_ERROR(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다"),
    POST_UNAUTHORIZED(HttpStatus.FORBIDDEN, "해당 게시글에 대한 수정 권한이 없습니다.");

    private HttpStatus status;
    private String message;

    PostError(HttpStatus status, String message) {
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
