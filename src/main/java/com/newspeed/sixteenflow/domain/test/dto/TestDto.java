package com.newspeed.sixteenflow.domain.test.dto;

import lombok.Getter;

@Getter
public class TestDto {

    private String name;
    private int age;

    public TestDto(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
