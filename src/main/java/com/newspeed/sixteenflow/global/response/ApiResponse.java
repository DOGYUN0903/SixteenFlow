package com.newspeed.sixteenflow.global.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.newspeed.sixteenflow.global.common.BaseCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@JsonPropertyOrder({ "statusCode", "message", "body" })
@NoArgsConstructor
public class ApiResponse<T> {

    @Builder
    private int statusCode;
    private String message;
    private T body;

    public ApiResponse(int statusCode, String message, T body) {
        this.statusCode = statusCode;
        this.message = message;
        this.body = body;
    }

    public static <T> ResponseEntity<ApiResponse<T>> toResponseEntity(BaseCode baseCode, T body) {
        return ResponseEntity
                .status(baseCode.getStatus())
                .body(new ApiResponse<>(baseCode.getStatus().value(), baseCode.getMessage(), body));
    }
}
