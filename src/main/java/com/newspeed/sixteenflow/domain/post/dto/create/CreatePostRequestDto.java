package com.newspeed.sixteenflow.domain.post.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class CreatePostRequestDto {

    @NotBlank(message = "게시글은 필수 입력입니다.")
    private String content;

    @Pattern(
            regexp = "^(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|jpeg|png|gif)$",
            message = "올바른 이미지 URL 형식이 아닙니다."
    )
    private String imageUrl;
}
