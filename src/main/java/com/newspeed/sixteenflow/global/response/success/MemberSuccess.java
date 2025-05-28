package com.newspeed.sixteenflow.global.response.success;

import com.newspeed.sixteenflow.global.common.BaseCode;
import org.springframework.http.HttpStatus;

public enum MemberSuccess implements BaseCode {
    MEMBER_SIGNUP(HttpStatus.CREATED, "회원가입이 완료되었습니다."),
    MEMBER_FIND(HttpStatus.OK, "프로필 조회에 성공하였습니다."),
    MEMBER_LOGIN(HttpStatus.OK, "로그인에 성공하였습니다."),
    MEMBER_LOGOUT(HttpStatus.OK, "로그아웃 하였습니다."),
    MEMBER_UPDATE_PROFILE(HttpStatus.OK, "프로필 수정이 완료되었습니다."),
    MEMBER_UPDATE_PASSWORD(HttpStatus.OK, "비밀번호가 수정되었습니다"),
    MEMBER_WITHDRAW(HttpStatus.OK, "회원탈퇴에 성공하였습니다.");

    private final HttpStatus status;
    private final String message;

    MemberSuccess(HttpStatus status, String message) {
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
