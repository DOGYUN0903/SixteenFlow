package com.newspeed.sixteenflow.domain.like.dto.PostLikeSearchDto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.newspeed.sixteenflow.global.common.PageResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public class PostLikeSearchResponseDto {
    private final Long postId;
    @JsonProperty("likeCount")
    private final int likeCommentCount;
    @JsonProperty("likedMember")
    private final PageResponse<PostLikeSearchDetailDto> pageResponse;
}