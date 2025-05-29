package com.newspeed.sixteenflow.domain.post.dto.update;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UpdatePostRequestDto {

    private String content;

    @Pattern(
            regexp = "^(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|jpeg|png|gif)$",
            message = "올바른 이미지 URL 형식이 아닙니다."
    )
    private String imageUrl;

    @AssertTrue(message = "수정할 항목을 최소 1개 이상 입력해야 합니다.")
    public boolean isAnyFieldUpdated() {
        return content != null || imageUrl != null;
    }
}
