package com.newspeed.sixteenflow.global.exception.member;

import com.newspeed.sixteenflow.global.common.BaseCode;
import com.newspeed.sixteenflow.global.exception.BaseException;

public class MemberException extends BaseException {
    private final BaseCode errorCode;

    public MemberException(BaseCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public BaseCode getErrorCode() {
        return errorCode;
    }
}
