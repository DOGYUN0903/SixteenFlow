package com.newspeed.sixteenflow.global.response.success;

import com.newspeed.sixteenflow.global.common.BaseCode;
import org.springframework.http.HttpStatus;

public enum LikeSuccess implements BaseCode {
    POST_LIKE_SUCCESS(HttpStatus.CREATED, "게시글 좋아요 성공"),
    POST_LIKE_CANCEL(HttpStatus.OK, "게시글 좋아요 취소"),
    COMMENT_LIKE_SUCCESS(HttpStatus.CREATED, "댓글 좋아요 성공"),
    COMMENT_LIKE_CANCEL(HttpStatus.OK, "댓글 좋아요 취소"),
    POST_LIKE_SEARCH_SUCCESS(HttpStatus.OK, "게시글 좋아요 검색 성공"),
    COMMENT_LIKE_SEARCH_SUCCESS(HttpStatus.OK, "댓글 좋아요 검색 성공");


    private final HttpStatus status;
    private final String message;

    LikeSuccess(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
