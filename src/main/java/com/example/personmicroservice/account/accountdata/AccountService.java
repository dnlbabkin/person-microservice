package com.example.personmicroservice.account.accountdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account saveUSD(Account account) {
        return accountRepository.save(account);
    }
}
