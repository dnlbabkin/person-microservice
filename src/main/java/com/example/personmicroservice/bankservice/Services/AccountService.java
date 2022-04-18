package com.example.personmicroservice.bankservice.Services;

import com.example.personmicroservice.AllData;
import com.example.personmicroservice.bankservice.Clients.CBRClient;
import com.example.personmicroservice.bankservice.Entity.Account;
import com.example.personmicroservice.bankservice.Repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CBRClient soapClient;

    public Account saveUSD(Account account) throws JAXBException {
        AllData.MainIndicatorsVR envelope = soapClient.getData();

        account.setUsd(envelope.getCurrency().getUSD().getCurs());

        return accountRepository.save(account);
    }
}
