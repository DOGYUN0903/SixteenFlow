package com.newspeed.sixteenflow.domain.auth.dto;

import lombok.Getter;

@Getter
public class LoginUserDto {

    private final String accessToken;

    public LoginUserDto(String accessToken) {
        this.accessToken = accessToken;
    }
}

