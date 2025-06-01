package com.newspeed.sixteenflow.global.response.error;

import com.newspeed.sixteenflow.global.common.BaseCode;
import org.springframework.http.HttpStatus;

public enum FollowError implements BaseCode {
    ALREADY_FOLLOW(HttpStatus.CONFLICT, "이미 팔로우된 사용자입니다."),
    FOLLOW_NOT_FOUND(HttpStatus.CONFLICT, "언팔로우 상태입니다."),
    CANNOT_FOLLOW_SELF(HttpStatus.BAD_REQUEST, "자기 자신은 팔로우할 수 없습니다."),
    CANNOT_UNFOLLOW_SELF(HttpStatus.BAD_REQUEST, "자기 자신은 언팔로우할 수 없습니다.");

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
