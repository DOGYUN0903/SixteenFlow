package com.newspeed.sixteenflow.domain.like.dto;

import lombok.Getter;

@Getter
public class PostLikeRequestDto {
    //현재 로그인 한 사람의 ID를 받아서 누가 좋아요를 눌렀는지 판별
    // 이후 JWT로 받을 것에 대비한 것입니다.
    private Long memberId;
}
