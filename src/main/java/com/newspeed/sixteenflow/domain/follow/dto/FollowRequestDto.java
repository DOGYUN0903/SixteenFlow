package com.newspeed.sixteenflow.domain.follow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class FollowRequestDto {
    @NotNull(message = "Id는 null일 수 없습니다.")
    @Positive(message = "Id는 양수여야 합니다.")
    private Long memberId;
}
