package com.newspeed.sixteenflow.domain.comments.dto;

import lombok.Getter;

@Getter
public class CommentMemberDto {
    private Long memberId;

    private String nickname;

    private String profileImageUrl;

    public CommentMemberDto(Long memberId, String nickname, String profileImageUrl) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }
}
