package com.example.personmicroservice.bankservice.Services;

import com.example.personmicroservice.Envelope;
import com.example.personmicroservice.bankservice.Clients.SoapClient;
import com.example.personmicroservice.bankservice.Configurations.UpdateDBConfig;
import com.example.personmicroservice.bankservice.Entity.Account;
import com.example.personmicroservice.bankservice.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final SoapClient soapClient;

    @Autowired
    public AccountService(AccountRepository accountRepository, SoapClient soapClient) {
        this.accountRepository = accountRepository;
        this.soapClient = soapClient;
    }

    public Account saveUSD(Account account) throws JAXBException {
        Envelope envelope = soapClient.getData();

        account.setUsd(envelope.getBody().getAllDataInfoXMLResponse()
                .getAllDataInfoXMLResult().getAllData()
                .getMainIndicatorsVR().getCurrency()
                .getUSD().getCurs());

        return accountRepository.save(account);
    }
}
