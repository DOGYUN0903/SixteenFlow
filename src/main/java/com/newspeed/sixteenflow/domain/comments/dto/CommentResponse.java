package com.newspeed.sixteenflow.domain.comments.dto;

import com.newspeed.sixteenflow.domain.comments.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private String NickName;

    private String ProfileImageUrl;

    private String content;

    private LocalDateTime modifiedAt;

    public CommentResponse(Comment comment) {
        this.NickName = comment.getMember().getNickname();
        this.ProfileImageUrl = comment.getMember().getProfileImageUrl();
        this.content = comment.getContent();
        this.modifiedAt = comment.getModifiedAt(); //BaseEntity에서 상속됌
    }

    public CommentResponse(String nickName, String profileImageUrl, String content, LocalDateTime modifiedAt) {
        NickName = nickName;
        ProfileImageUrl = profileImageUrl;
        this.content = content;
        this.modifiedAt = modifiedAt;
    }
}
