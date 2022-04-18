package com.example.personmicroservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConfigurationProperties(prefix = "external")
public class ExternalApiParams {

    ApiClientParams cbr;
    ApiClientParams anotherapi;

    @PostConstruct
    public void init() {
//        System.out.println(cbr);
    }
}
