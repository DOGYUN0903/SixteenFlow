package com.newspeed.sixteenflow.domain.comments.dto;

//더미 리스폰스용 클래스입니다.

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentListResponseDto {
    private Long memberId;
    private String nickname;
    private String profileImageUrl;
    private String content;
    private LocalDateTime updatedAt;
}
