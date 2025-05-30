package com.newspeed.sixteenflow.domain.comments.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


public class CreateCommentRequest {

    //다른 테이블(member)과 결합해서 어떻게 사용하는가?
    //멤버 테이블로부터 닉네임, 프로필 사진 url을 받아서 contetn와 리스폰스dto를 만들어야 된다.
    //댓글 달릴 때 아래 3요소가 맞는지?


    @NotBlank(message = "댓글은 필수입니다.")
    private String content;

    private Long memberId;

    private Long postId;

    public String getContent() {
        return content;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getPostId() {
        return postId;
    }
}
