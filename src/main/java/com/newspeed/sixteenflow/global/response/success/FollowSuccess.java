package com.newspeed.sixteenflow.global.response.success;

import com.newspeed.sixteenflow.global.common.BaseCode;
import org.springframework.http.HttpStatus;

public enum FollowSuccess implements BaseCode {

    SUCCESS_RESPONSE(HttpStatus.OK, "Success"),;

    private HttpStatus status;
    private String message;

    FollowSuccess(HttpStatus status, String message) {
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
