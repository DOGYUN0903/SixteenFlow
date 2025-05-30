package com.newspeed.sixteenflow.global.exception.commnet;

import com.newspeed.sixteenflow.global.common.BaseCode;
import com.newspeed.sixteenflow.global.exception.BaseException;
import com.newspeed.sixteenflow.global.response.error.CommentError;

public class CommentException extends BaseException {

  @Override
  public BaseCode getErrorCode() {return CommentError.COMMENT_ERROR;}
}
