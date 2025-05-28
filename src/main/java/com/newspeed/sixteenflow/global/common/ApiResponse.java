package com.newspeed.sixteenflow.global.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@JsonPropertyOrder({ "statusCode", "message", "body" })
public class ApiResponse<T> {

    private int statusCode;
    private String message;
    private T body;

    private ApiResponse() {}

    private ApiResponse(int statusCode, String message, T body) {
        this.statusCode = statusCode;
        this.message = message;
        this.body = body;
    }

    public static Builder<?> status(BaseCode baseCode) {
        return new Builder<>(baseCode);
    }

    public static class Builder<T> {
        private final ApiResponse<T> response;

        private Builder(BaseCode baseCode) {
            response = new ApiResponse<>();
            response.statusCode = baseCode.getStatus().value();
            response.message = baseCode.getMessage();
        }

        public <U> ResponseEntity<ApiResponse<U>> body(){
            return ResponseEntity
                    .status(response.statusCode)
                    .body(new ApiResponse<>(response.statusCode,response.message,null));
        }

        public <U> ResponseEntity<ApiResponse<U>> body(U body){
            return ResponseEntity
                    .status(response.statusCode)
                    .body(new ApiResponse<>(response.statusCode,response.message,body));
        }
    }
}
