package com.newspeed.sixteenflow.domain.like.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostLikeResponseDto {
    private final Long postId;
    private final int likePostCount;
    private final boolean like; // 현재 좋아요 상태 (toggle용)
}
