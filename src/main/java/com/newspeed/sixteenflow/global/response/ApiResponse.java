package com.newspeed.sixteenflow.global.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonPropertyOrder({ "statusCode", "message", "body" })
@NoArgsConstructor
public class ApiResponse<T> {

    private int statusCode;
    private String message;
    private T body;

    public ApiResponse(int statusCode, String message, T body) {
        this.statusCode = statusCode;
        this.message = message;
        this.body = body;
    }
}
