package com.newspeed.sixteenflow.domain.test;

import com.newspeed.sixteenflow.domain.test.dto.TestDto;
import com.newspeed.sixteenflow.global.common.ApiResponse;
import com.newspeed.sixteenflow.global.common.PageResponse;
import com.newspeed.sixteenflow.global.exception.test.TestException;
import com.newspeed.sixteenflow.global.response.success.TestSuccess;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestRepository testRepository;
    @PostMapping("/test")
    public ResponseEntity<ApiResponse<TestDto>> test(
            @Valid @RequestBody TestDto testDto
    ) {
         return ApiResponse.status(TestSuccess.TEST_SUCCESS).body(testDto);
        //return ApiResponse.status(TestEnum.TEST_SUCCESS).body();
    }

    @GetMapping("test2")
    public ResponseEntity<ApiResponse<TestDto>> test2() {

        throw new TestException();

        //TestDto testDto = new TestDto("이름1", 13);
        //return ApiResponse.status(TestEnum.TEST_SUCCESS).body(testDto);
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse<PageResponse<TestDto>>> page(Pageable pageable) {
        Page<Test> page = testRepository.findAll(pageable);
        Page<TestDto> dtoPage = page.map(TestDto::toDto);
        return ApiResponse.status(TestSuccess.TEST_SUCCESS).body(new PageResponse<>(dtoPage));
    }


}
