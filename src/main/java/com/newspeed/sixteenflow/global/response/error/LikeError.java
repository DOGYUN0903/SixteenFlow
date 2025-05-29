package com.newspeed.sixteenflow.global.response.error;

import com.newspeed.sixteenflow.global.common.BaseCode;
import org.springframework.http.HttpStatus;

public enum LikeError implements BaseCode {
    //MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    COMMENT_ERROR(HttpStatus.NOT_FOUND, "존재하지 않는 댓글입니다."),
    POST_ERROR(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다.");

    private final HttpStatus status;
    private final String message;

    LikeError(HttpStatus status, String message) {
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

