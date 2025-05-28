package com.newspeed.sixteenflow.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberResponseDto {
    private final String email;
    private final String profileImageUrl;
    private final String username;
    private final String nickname;
    private final String address;
    private final String phoneNumber;
    private final Long followingCount;
    private final Long followerCount;
    }
