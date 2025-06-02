package com.newspeed.sixteenflow.domain.like.dto.CommentLikeSearchDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentLikeSearchDetailDto {
    private final Long memberId;
    private final String profileImageUrl;
    private final String nickname;
}
