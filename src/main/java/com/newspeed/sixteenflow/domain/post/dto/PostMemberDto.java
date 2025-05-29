package com.newspeed.sixteenflow.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostMemberDto {

    private Long memberId;

    private String nickname;

    private String profileImageUrl;
}
