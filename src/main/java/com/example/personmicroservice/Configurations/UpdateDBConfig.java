package com.example.personmicroservice.Configurations;

import com.example.personmicroservice.Clients.SoapClient;
import com.example.personmicroservice.Entity.Account;
import com.example.personmicroservice.Envelope;
import com.example.personmicroservice.Repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.xml.bind.JAXBException;

@EnableScheduling
@Configuration
public class UpdateDBConfig {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SoapClient soapClient;

    private final Logger logger = LoggerFactory.getLogger(UpdateDBConfig.class);

    @Scheduled(fixedRateString = "${sample.schedule.string}", initialDelay = 1000)
    public void updateDataBase() throws JAXBException {
        logger.info("Start writing...");

        Account account = new Account();
        Envelope envelope = soapClient.getData();

        account.setUsd(envelope.getBody().getAllDataInfoXMLResponse()
                .getAllDataInfoXMLResult().getAllData()
                .getMainIndicatorsVR().getCurrency()
                .getUSD().getCurs());

        accountRepository.save(account);

        logger.info("Finish writing...");
    }
}
