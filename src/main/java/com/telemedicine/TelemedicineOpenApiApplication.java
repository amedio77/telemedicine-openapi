package com.telemedicine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TelemedicineOpenApiApplication {

    public static void main(String[] args) {
        log.debug("pppp");
        SpringApplication.run(TelemedicineOpenApiApplication.class, args);
    }
}
