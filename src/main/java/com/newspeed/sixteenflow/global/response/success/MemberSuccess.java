package com.newspeed.sixteenflow.global.response.success;

import com.newspeed.sixteenflow.global.common.BaseCode;
import org.springframework.http.HttpStatus;

public enum MemberSuccess implements BaseCode {
    ;

    private HttpStatus status;
    private String message;

    @Override
    public HttpStatus getStatus() {
        return null;
    }

    @Override
    public String getMessage() {
        return "";
    }
}
