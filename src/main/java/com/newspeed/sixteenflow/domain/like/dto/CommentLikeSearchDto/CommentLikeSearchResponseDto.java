package com.newspeed.sixteenflow.domain.like.dto.CommentLikeSearchDto;


import com.newspeed.sixteenflow.global.common.PageResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentLikeSearchResponseDto {
    private final Long commentId;
    private final PageResponse<CommentLikeSearchDetailDto> pageResponse;

}
