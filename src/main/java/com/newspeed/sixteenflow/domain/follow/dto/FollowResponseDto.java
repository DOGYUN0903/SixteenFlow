package com.newspeed.sixteenflow.domain.follow.dto;

import com.newspeed.sixteenflow.domain.follow.entity.Follow;
import lombok.Getter;

@Getter
public class FollowResponseDto {

    private Long followerId;
    private Long followingId;

    public static FollowResponseDto toDto(Follow follow) {
        FollowResponseDto dto = new FollowResponseDto();
        dto.followerId = follow.getFollower().getId();
        dto.followingId = follow.getFollowing().getId();
        return dto;
    }
}
