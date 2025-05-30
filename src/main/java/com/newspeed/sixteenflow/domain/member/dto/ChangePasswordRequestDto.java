package com.newspeed.sixteenflow.domain.member.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class ChangePasswordRequestDto {
    private final String oldPassword;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+\\[{\\]};:'\",<.>/?]).{8,}$",
            message = "비밀번호는 최소 8글자 이상, 대소문자 포함 영문 + 숫자 + 특수문자를 최소 1글자씩 포함해야합니다."
    )
    private final String newPassword;

    public ChangePasswordRequestDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
