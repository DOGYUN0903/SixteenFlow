package com.newspeed.sixteenflow.global.response.success;

import com.newspeed.sixteenflow.global.common.BaseCode;
import org.springframework.http.HttpStatus;

public enum TestSuccess implements BaseCode {
    TEST_SUCCESS(HttpStatus.OK, "성공했습니다.");

    private HttpStatus status;
    private String message;

    TestSuccess(HttpStatus status, String message) {
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
