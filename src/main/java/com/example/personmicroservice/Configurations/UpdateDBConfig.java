package com.example.personmicroservice.Configurations;

import com.example.personmicroservice.Clients.SoapClient;
import com.example.personmicroservice.Entity.Account;
import com.example.personmicroservice.Envelope;
import com.example.personmicroservice.Repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.xml.bind.JAXBException;

@Slf4j
@EnableScheduling
@Configuration
public class UpdateDBConfig {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SoapClient soapClient;

    @Scheduled(fixedRateString = "${sample.schedule.string}", initialDelayString = "${initialdelay.string}")
    public void updateDataBase() throws JAXBException {
        Account account = new Account();
        Envelope envelope = soapClient.getData();

        account.setUsd(envelope.getBody().getAllDataInfoXMLResponse()
                .getAllDataInfoXMLResult().getAllData()
                .getMainIndicatorsVR().getCurrency()
                .getUSD().getCurs());

        accountRepository.save(account);

        log.info("Finish writing Course: [" + account.getUsd()
                + "] to Database. Entity[" + account + "]");
    }
}
