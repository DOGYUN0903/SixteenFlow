package com.newspeed.sixteenflow.global.exception.follow;

import com.newspeed.sixteenflow.global.common.BaseCode;
import com.newspeed.sixteenflow.global.exception.BaseException;
import com.newspeed.sixteenflow.global.response.error.FollowError;

public class FollowNotFoundException extends BaseException {
    @Override
    public BaseCode getErrorCode() {
        return FollowError.FOLLOW_NOT_FOUND;
    }
}
