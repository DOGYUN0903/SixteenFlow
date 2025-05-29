package com.newspeed.sixteenflow.global.exception.post;

import com.newspeed.sixteenflow.global.common.BaseCode;
import com.newspeed.sixteenflow.global.exception.BaseException;
import com.newspeed.sixteenflow.global.response.error.PostError;

public class PostNotFoundException extends BaseException {
    @Override
    public BaseCode getErrorCode() {
        return PostError.POST_ERROR;
    }
}
