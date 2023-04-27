package ru.hogwards.school;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@OpenAPIDefinition
public class HogwardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HogwardsApplication.class, args);
    }

}

