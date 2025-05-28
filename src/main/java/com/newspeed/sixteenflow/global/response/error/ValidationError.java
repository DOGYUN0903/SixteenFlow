package com.newspeed.sixteenflow.global.response.error;

import com.newspeed.sixteenflow.global.common.BaseCode;
import org.springframework.http.HttpStatus;

public enum ValidationError implements BaseCode {
    VALIDATION_FIELD_ERROR(HttpStatus.BAD_REQUEST, "입력값이 올바르지 않습니다."),
    MISSING_REQUEST_BODY(HttpStatus.BAD_REQUEST, "요청 본문이 비어있습니다."),;

    private HttpStatus status;
    private String message;

    ValidationError(HttpStatus status, String message) {
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
