package com.newspeed.sixteenflow.global.exception;

import com.newspeed.sixteenflow.global.common.BaseCode;

public abstract class BaseException extends RuntimeException {
    public abstract BaseCode getErrorCode();
}
