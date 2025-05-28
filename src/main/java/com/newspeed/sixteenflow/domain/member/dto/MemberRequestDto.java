package com.newspeed.sixteenflow.domain.member.dto;

import lombok.Getter;

@Getter
public class MemberRequestDto {
    private final String email;
    private final String profileImageUrl;
    private final String password;
    private final String username;
    private final String nickname;
    private final String address;
    private final String phoneNumber;

    public MemberRequestDto(String email, String profileImageUrl, String password, String username, String nickname, String address, String phoneNumber) {
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
