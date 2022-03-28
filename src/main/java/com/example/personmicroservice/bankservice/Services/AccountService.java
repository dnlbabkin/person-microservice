package com.example.personmicroservice.bankservice.Services;

import com.example.personmicroservice.bankservice.Configurations.UpdateDBConfig;
import com.example.personmicroservice.bankservice.Entity.Account;
import com.example.personmicroservice.bankservice.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UpdateDBConfig updateDBConfig;

    public Account saveUSD(Account account) {
        return accountRepository.save(account);
    }
}
