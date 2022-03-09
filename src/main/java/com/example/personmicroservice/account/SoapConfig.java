package com.example.personmicroservice.account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;

@Configuration
public class SoapConfig {

    @Bean
    public Jaxb2Marshaller marshaller () {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("com.example.personmicroservice");

        return marshaller;
    }
}
