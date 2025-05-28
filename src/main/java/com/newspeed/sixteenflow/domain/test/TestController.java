package com.newspeed.sixteenflow.domain.test;

import com.newspeed.sixteenflow.domain.test.dto.TestDto;
import com.newspeed.sixteenflow.global.common.ApiResponse;
import com.newspeed.sixteenflow.global.exception.test.TestException;
import com.newspeed.sixteenflow.global.response.success.TestSuccess;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<ApiResponse<TestDto>> test() {
        TestDto testDto = new TestDto("이름1", 13);
         //return ApiResponse.status(TestSuccess.TEST_SUCCESS).body(testDto);
        return ApiResponse.status(TestSuccess.TEST_SUCCESS).body();
    }

    @GetMapping("test2")
    public ResponseEntity<ApiResponse<TestDto>> test2() {

        throw new TestException();

        //TestDto testDto = new TestDto("이름1", 13);
        //return ApiResponse.status(TestEnum.TEST_SUCCESS).body(testDto);
    }

}
