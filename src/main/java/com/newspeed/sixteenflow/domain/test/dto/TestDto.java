package com.newspeed.sixteenflow.domain.test.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TestDto {
    @NotNull(message = "이름은 필수값 입니다.")
    private String name;
    private int age;
}
