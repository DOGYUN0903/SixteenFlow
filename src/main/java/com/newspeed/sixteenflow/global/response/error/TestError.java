package com.newspeed.sixteenflow.global.response.error;

import com.newspeed.sixteenflow.global.common.BaseCode;
import org.springframework.http.HttpStatus;

public enum TestError implements BaseCode {
    TEST_ERROR(HttpStatus.BAD_REQUEST, "존재하지 않는 ....");
    private HttpStatus status;
    private String message;

    TestError(HttpStatus status, String message) {
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
