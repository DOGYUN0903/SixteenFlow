package com.newspeed.sixteenflow.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberUpdateResponseDto {

    private final String email;

    private final String profileImageUrl;

    private final String nickname;

    private final String address;

    private final String phoneNumber;

    private final LocalDateTime modifiedAt;

    public static MemberUpdateResponseDto toDto(Member member) {
        return MemberUpdateResponseDto.builder()
                .email(member.getEmail())
                .profileImageUrl(member.getProfileImageUrl())
                .nickname(member.getNickname())
                .address(member.getAddress())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}
