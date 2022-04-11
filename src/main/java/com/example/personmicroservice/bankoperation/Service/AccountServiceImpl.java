package com.example.personmicroservice.bankoperation.Service;

import com.example.personmicroservice.bankoperation.Model.Transaction;
import com.example.personmicroservice.bankservice.Entity.PersonAccount;
import com.example.personmicroservice.bankoperation.Model.TransferRequest;
import com.example.personmicroservice.bankoperation.Repositories.TransactionRepository;
import com.example.personmicroservice.bankservice.Repository.PersonAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private PersonAccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction sendMoney(TransferRequest transferRequest) {
        String fromAccountNumber = transferRequest.getFromAccountNumber();
        String toAccountNumber = transferRequest.getToAccountNumber();
        String currency = transferRequest.getCurrency();
        BigDecimal amount = transferRequest.getAmount();
        
        PersonAccount fromAccount = accountRepository.findByAccountNumberEquals(fromAccountNumber);
        PersonAccount toAccount = accountRepository.findByAccountNumberEquals(toAccountNumber);

        fromAccount.setCurrentAmount(fromAccount.getCurrentAmount().subtract(amount));

        accountRepository.save(fromAccount);

        toAccount.setCurrentAmount(toAccount.getCurrentAmount().add(amount));

        accountRepository.save(toAccount);

        Transaction transaction = transactionRepository.save(new Transaction(0L, toAccountNumber, currency, amount));

        return transaction;
    }
}
