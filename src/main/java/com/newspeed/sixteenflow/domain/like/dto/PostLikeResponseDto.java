package com.newspeed.sixteenflow.domain.like.dto;

import lombok.Getter;

@Getter
public class PostLikeResponseDto {
    private Long postId;
    private int likePostCount;
    private boolean like; // 현재 좋아요 상태 (toggle용)

    public PostLikeResponseDto(Long postId, int likePostCount, boolean like) {
        this.postId = postId;
        this.likePostCount = likePostCount; // 좋아요 수
        this.like = like; //좋아요 상태를 한번 더 알려줌 (없어도 됨)
    }
}
