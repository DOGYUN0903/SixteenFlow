package com.newspeed.sixteenflow.domain.comments.dto;

import com.newspeed.sixteenflow.domain.comments.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateCommentResponse {
    private Long id;
    private String content;
    private LocalDateTime modifiedAt;

    public UpdateCommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.modifiedAt = comment.getModifiedAt();
    }
}
