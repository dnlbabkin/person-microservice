package com.example.personmicroservice.bankservice.Configurations;

import com.example.personmicroservice.AllData;
import com.example.personmicroservice.bankservice.Clients.CBRClient;
import com.example.personmicroservice.bankservice.Entity.Account;
import com.example.personmicroservice.bankservice.Repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.xml.bind.JAXBException;

@Slf4j
@EnableScheduling
@Configuration
@RequiredArgsConstructor
public class UpdateDBConfig {

    private final AccountRepository accountRepository;
    private final CBRClient soapClient;

    @Scheduled(fixedRateString = "${sample.schedule.string}", initialDelayString = "${initialdelay.string}")
    public void updateDataBase() throws JAXBException {
        Account account = new Account();
        AllData.MainIndicatorsVR envelope = soapClient.getData();

        account.setUsd(envelope.getCurrency().getUSD().getCurs());

        accountRepository.save(account);

        log.info("Finish writing Course: [" + account.getUsd()
                + "] to Database. Entity[ Id = " + account.getId() + ", USD = "
        + account.getUsd() + " ]");

    }
}
