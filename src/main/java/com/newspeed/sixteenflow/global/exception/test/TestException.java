package com.newspeed.sixteenflow.global.exception.test;

import com.newspeed.sixteenflow.global.common.BaseCode;
import com.newspeed.sixteenflow.global.exception.BaseException;
import com.newspeed.sixteenflow.global.response.error.TestError;
import com.newspeed.sixteenflow.global.response.success.TestSuccess;

public class TestException extends BaseException {
    @Override
    public BaseCode getErrorCode() {
        return TestError.TEST_ERROR;
    }
}
