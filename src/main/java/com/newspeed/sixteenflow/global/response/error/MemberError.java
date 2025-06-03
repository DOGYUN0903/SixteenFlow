package com.newspeed.sixteenflow.global.response.error;

import com.newspeed.sixteenflow.global.common.BaseCode;
import org.springframework.http.HttpStatus;

public enum MemberError implements BaseCode {
    MEMBER_EMAIL_EXIST(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다."),
    MEMBER_NICKNAME_EXIST(HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다."),
    MEMBER_PHONE_NUMBER_EXIST(HttpStatus.CONFLICT, "이미 존재하는 전화번호입니다"),
    MEMBER_LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "존재하지 않는 이메일이거나 비밀번호가 일치하지 않습니다."),
    MEMBER_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    MEMBER_TOKEN_MALFORMED(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    MEMBER_UNAUTHORIZED(HttpStatus.FORBIDDEN, "본인만 수정 및 삭제가 가능합니다."),
    MEMBER_INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    MEMBER_SAME_PASSWORD(HttpStatus.BAD_REQUEST, "현재 비밀번호와 동일합니다."),
    MEMBER_DELETED(HttpStatus.FORBIDDEN, "탈퇴한 회원입니다"),
    MEMBER_NO_UPDATE_FIELDS(HttpStatus.BAD_REQUEST, "수정할 내용이 없습니다." );

    private final HttpStatus status;
    private final String message;

    MemberError(HttpStatus status, String message) {
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