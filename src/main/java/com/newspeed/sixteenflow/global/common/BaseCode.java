package com.newspeed.sixteenflow.global.common;

import org.springframework.http.HttpStatus;

public interface BaseCode {
    HttpStatus getStatus();
    String getMessage();
}
