package com.newspeed.sixteenflow.domain.comments.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateCommentRequest {
    @NotBlank(message = "댓글 내용을 비워둘 수 없습니다.")
    private String content;

    public String getContent() {
        return content;
    }
}
