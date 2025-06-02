package com.newspeed.sixteenflow.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.newspeed.sixteenflow.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class MemberResponseDto {

    private final Long id;

    private final String email;

    private final String profileImageUrl;

    private final String username;

    private final String nickname;

    private final String address;

    private final String phoneNumber;

    private final Long followCount;

    private final Long followerCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime modifiedAt;

    public static MemberResponseDto toDto(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .profileImageUrl(member.getProfileImageUrl())
                .username(member.getUsername())
                .nickname(member.getNickname())
                .address(member.getAddress())
                .phoneNumber(member.getPhoneNumber())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static MemberResponseDto toDetailProfileDto(Member member, Long followCount, Long followerCount) {
        return MemberResponseDto.builder()
                .email(member.getEmail())
                .profileImageUrl(member.getProfileImageUrl())
                .username(member.getUsername())
                .nickname(member.getNickname())
                .address(member.getAddress())
                .phoneNumber(member.getPhoneNumber())
                .followCount(followCount)
                .followerCount(followerCount)
                .createdAt(member.getCreatedAt())
                .modifiedAt(member.getModifiedAt())
                .build();
    }

    public static MemberResponseDto toPublicProfileDto(Member member, Long followCount, Long followerCount) {
        return MemberResponseDto.builder()
                .profileImageUrl(member.getProfileImageUrl())
                .nickname(member.getNickname())
                .followCount(followCount)
                .followerCount(followerCount)
                .createdAt(member.getCreatedAt())
                .modifiedAt(member.getModifiedAt())
                .build();
    }
}

