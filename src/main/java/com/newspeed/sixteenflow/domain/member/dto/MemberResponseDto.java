package com.newspeed.sixteenflow.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class MemberResponseDto {
    private final Long id;
    private final String email;
    private final String profileImageUrl;
    private final String username;
    private final String nickname;
    private final String address;
    private final String phoneNumber;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final Long followingCount;
    private final Long followerCount;
}
