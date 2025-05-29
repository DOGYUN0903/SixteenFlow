package com.newspeed.sixteenflow.domain.test.dto;

import com.newspeed.sixteenflow.domain.test.Test;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TestDto {
    @NotNull(message = "이름은 필수값 입니다.")
    private String name;
    private int age;

    public static TestDto toDto(Test test) {
        TestDto dto = new TestDto();
        dto.name = test.getName();
        dto.age = test.getAge();
        return dto;
    }
}
