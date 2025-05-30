package com.newspeed.sixteenflow.domain.like.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentLikeResponseDto {
    private final Long commentId;
    private final int likeCommentCount;
    private final boolean like; // 현재 좋아요 상태 (toggle용)
}
