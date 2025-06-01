package com.newspeed.sixteenflow.global.exception.follow;

import com.newspeed.sixteenflow.global.common.BaseCode;
import com.newspeed.sixteenflow.global.exception.BaseException;
import com.newspeed.sixteenflow.global.response.error.FollowError;

public class CannotUnFollowSelfException extends BaseException {
    @Override
    public BaseCode getErrorCode() {
        return FollowError.CANNOT_UNFOLLOW_SELF;
    }
}
