package com.newspeed.sixteenflow.domain.member.dto;

import lombok.Getter;

@Getter
public class LoginUserDto {
    private final Long id;

    private final String username;

    public LoginUserDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
