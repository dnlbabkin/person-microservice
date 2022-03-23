package com.example.personmicroservice.Services;

import com.example.personmicroservice.Entity.Account;
import com.example.personmicroservice.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account saveUSD(Account account) {
        return accountRepository.save(account);
    }

}
