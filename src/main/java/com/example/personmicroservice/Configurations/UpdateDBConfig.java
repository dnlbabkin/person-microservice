package com.example.personmicroservice.Configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.xml.bind.JAXBException;

@EnableScheduling
@Configuration
public class UpdateDBConfig {

    @Scheduled(fixedRateString = "${sample.schedule.string}", initialDelay = 1000)
    public void updateDataBase() throws JAXBException {

    }
}
