package com.boot.pp25;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BootCrudApplication {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .basicAuthentication("serverSecretAdmin", "secretPassword")
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(BootCrudApplication.class, args);
    }

}
