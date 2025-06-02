package com.newspeed.sixteenflow.domain.like.dto.PostLikeSearchDto;


import com.newspeed.sixteenflow.global.common.PageResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public class PostLikeSearchResponseDto {
    private final Long postId;
    private final PageResponse<PostLikeSearchDetailDto> pageResponse;
}
