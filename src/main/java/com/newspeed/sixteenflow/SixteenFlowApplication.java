package com.newspeed.sixteenflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SixteenFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(SixteenFlowApplication.class, args);
    }

}
