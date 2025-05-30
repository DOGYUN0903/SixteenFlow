package com.newspeed.sixteenflow.domain.like.dto.CommentLikeSearchDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CommentLikeSearchListResponseDto {
    private final Long CommentId;
    private final int likeCommentCount;
    private final List<CommentLikeSearchDetailDto> likeMembers;
}
