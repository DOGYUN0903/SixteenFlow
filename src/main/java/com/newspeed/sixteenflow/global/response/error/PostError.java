package com.newspeed.sixteenflow.global.response.error;

import com.newspeed.sixteenflow.global.common.BaseCode;
import org.springframework.http.HttpStatus;

public enum PostError implements BaseCode {

    POST_ERROR(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다");

    private HttpStatus status;
    private String message;

    PostError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return null;
    }

    @Override
    public String getMessage() {
        return "";
    }
}
