package com.newspeed.sixteenflow.domain.post.dto.create;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreatePostRequestDto {

    @NotBlank(message = "게시글은 필수 입력입니다.")
    private String content;

    private String imageUrl;
}
