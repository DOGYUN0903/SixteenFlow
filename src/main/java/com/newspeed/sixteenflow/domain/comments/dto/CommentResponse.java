package com.newspeed.sixteenflow.domain.comments.dto;

import com.newspeed.sixteenflow.domain.comments.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {

    private Long id;

    private CommentMemberDto member;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.member = new CommentMemberDto(
                comment.getMember().getId(),
                comment.getMember().getNickname(),
                comment.getMember().getProfileImageUrl()
        );
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt(); //BaseEntity에서 상속됌
    }

}
