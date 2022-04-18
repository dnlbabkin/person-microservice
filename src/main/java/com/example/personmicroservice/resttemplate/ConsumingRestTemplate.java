package com.example.personmicroservice.resttemplate;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ConsumingRestTemplate {

    @Bean
    private RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
