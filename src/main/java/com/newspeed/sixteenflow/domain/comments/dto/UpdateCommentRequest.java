package com.newspeed.sixteenflow.domain.comments.dto;

import jakarta.validation.constraints.NotNull;

public class UpdateCommentRequest {
    @NotNull
    private String content;

    public String getContent() {
        return content;
    }
}
