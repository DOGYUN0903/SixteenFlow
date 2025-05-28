package com.newspeed.sixteenflow.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class MemberRequestDto {
    @NotBlank
    @Email
    private final String email;
    private final String profileImageUrl;
    @NotBlank
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+\\[{\\]};:'\",<.>/?]).{8,}$",
            message = "비밀번호는 최소 8글자 이상, 대소문자 포함 영문 + 숫자 + 특수문자를 최소 1글자씩 포함해야합니다."
    )
    private final String password;
    @NotBlank
    private final String username;
    @NotBlank
    private final String nickname;
    private final String address;
    @Pattern(regexp = "^(010|011|016|017|018|019)-\\d{3,4}$")
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
