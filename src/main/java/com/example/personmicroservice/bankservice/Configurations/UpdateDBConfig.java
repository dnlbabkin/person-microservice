package com.example.personmicroservice.bankservice.Configurations;

import com.example.personmicroservice.bankservice.Clients.SoapClient;
import com.example.personmicroservice.bankservice.Entity.Account;
import com.example.personmicroservice.Envelope;
import com.example.personmicroservice.bankservice.Repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.EntityManager;
import javax.xml.bind.JAXBException;
import java.math.BigDecimal;

@Slf4j
@EnableScheduling
@Configuration
public class UpdateDBConfig {

    private final AccountRepository accountRepository;
    private final SoapClient soapClient;

    @Autowired
    public UpdateDBConfig(AccountRepository accountRepository, SoapClient soapClient) {
        this.accountRepository = accountRepository;
        this.soapClient = soapClient;
    }

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
                + "] to Database. Entity[ Id = " + account.getId() + ", USD = "
        + account.getUsd() + " ]");

    }
}
