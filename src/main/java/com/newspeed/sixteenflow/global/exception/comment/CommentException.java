package com.newspeed.sixteenflow.global.exception.comment;

import com.newspeed.sixteenflow.global.common.BaseCode;
import com.newspeed.sixteenflow.global.exception.BaseException;

public class CommentException extends BaseException {
  private final BaseCode commentErrorCode;

  public CommentException(BaseCode commentErrorCode) {
    this.commentErrorCode = commentErrorCode;
  }

  @Override
  public BaseCode getErrorCode() {return commentErrorCode;}
}
