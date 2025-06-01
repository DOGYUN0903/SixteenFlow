package com.newspeed.sixteenflow.global.response.error;

import com.newspeed.sixteenflow.global.common.BaseCode;
import org.springframework.http.HttpStatus;

public enum FollowError implements BaseCode {
    ALREADY_FOLLOW(HttpStatus.CONFLICT, "이미 팔로우된 사용자입니다."),
    ALERADY_UNFOLLOW(HttpStatus.NO_CONTENT, "언팔로우 상태입니다.");

    private HttpStatus status;
    private String message;

    FollowError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() { return status; }

    @Override
    public String getMessage() {
        return message;
    }
}
