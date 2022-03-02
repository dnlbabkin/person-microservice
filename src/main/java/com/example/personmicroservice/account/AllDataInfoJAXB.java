package com.example.personmicroservice.account;

import com.example.personmicroservice.AllDataInfoXML;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class AllDataInfoJAXB {

    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("src/main/resources/DailyInfo.wsdl");

        return marshaller;
    }
}
