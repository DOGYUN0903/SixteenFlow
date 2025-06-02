package com.newspeed.sixteenflow.domain.comments.dto;

import com.newspeed.sixteenflow.domain.comments.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {

    private Long id;

    private String nickName;

    private String profileImageUrl;

    private String email;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.nickName = comment.getMember().getNickname();
        this.email = comment.getMember().getEmail();
        this.profileImageUrl = comment.getMember().getProfileImageUrl();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt(); //BaseEntity에서 상속됌
    }

    public CommentResponse(String nickName, String profileImageUrl, String content, LocalDateTime modifiedAt) {
        nickName = nickName;
        profileImageUrl = profileImageUrl;
        this.content = content;
        this.modifiedAt = modifiedAt;
    }
}
