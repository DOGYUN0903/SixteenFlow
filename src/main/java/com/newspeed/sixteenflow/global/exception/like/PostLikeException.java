package com.newspeed.sixteenflow.global.exception.like;

import com.newspeed.sixteenflow.global.common.BaseCode;
import com.newspeed.sixteenflow.global.exception.BaseException;
import com.newspeed.sixteenflow.global.response.error.LikeError;

public class PostLikeException extends BaseException {
    @Override
    public BaseCode getErrorCode() {
        return LikeError.POST_ERROR;
    }



}
