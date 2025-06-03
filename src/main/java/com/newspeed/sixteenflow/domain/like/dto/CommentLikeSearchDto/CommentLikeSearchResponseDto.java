package com.newspeed.sixteenflow.domain.like.dto.CommentLikeSearchDto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.newspeed.sixteenflow.global.common.PageResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentLikeSearchResponseDto {
    private final Long commentId;
    @JsonProperty("likeCount")
    private final int likeCommentCount;
    @JsonProperty("likedMember")
    private final PageResponse<CommentLikeSearchDetailDto> pageResponse;

}
