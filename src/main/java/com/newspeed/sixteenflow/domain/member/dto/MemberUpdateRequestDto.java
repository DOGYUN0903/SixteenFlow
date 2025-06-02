package com.newspeed.sixteenflow.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

@Getter
public class MemberUpdateRequestDto {
    @Email
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "올바른 이메일 형식이 아닙니다."
    )
    private final String email;

    @URL(message = "유효하지 않은 url 입니다.")
    private final String profileImageUrl;

    private final String nickname;

    private final String address;

    @Pattern(regexp = "^(010|011|016|017|018|019)\\d{7,8}$", message = "'-'없이 핸드폰 번호를 입력해주세요.")
    private final String phoneNumber;

    public MemberUpdateRequestDto(String email, String profileImageUrl, String nickname, String address, String phoneNumber) {
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
