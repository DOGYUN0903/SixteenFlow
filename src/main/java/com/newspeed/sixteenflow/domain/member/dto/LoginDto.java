package com.newspeed.sixteenflow.domain.member.dto;

import lombok.Getter;

@Getter
public class LoginDto {
    private final Long id;
    private final String username;

    public LoginDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
