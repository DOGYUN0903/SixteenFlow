package com.newspeed.sixteenflow.domain.like.dto.PostLikeSearchDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PostLikeSearchListResponseDto {
    private final Long postId;
    private final int likePostCount;
    private final List<PostLikeSearchDetailDto> likeMembers;
}
