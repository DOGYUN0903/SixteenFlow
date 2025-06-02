package com.newspeed.sixteenflow.domain.like.dto.PostLikeSearchDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostLikeSearchDetailDto {
    private final Long memberId;
    private final String profileImageUrl;
    private final String nickname;
}
