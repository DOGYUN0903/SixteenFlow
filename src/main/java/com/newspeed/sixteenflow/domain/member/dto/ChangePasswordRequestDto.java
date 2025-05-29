package com.newspeed.sixteenflow.domain.member.dto;

import lombok.Getter;

@Getter
public class ChangePasswordRequestDto {
    private final String oldPassword;
    private final String newPassword;

    public ChangePasswordRequestDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
