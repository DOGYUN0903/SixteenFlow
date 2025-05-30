package com.newspeed.sixteenflow.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.newspeed.sixteenflow.domain.follow.dto.FollowCountDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime modifiedAt;

    private final FollowCountDto followCountDto;
}
