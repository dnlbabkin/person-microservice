package com.example.personmicroservice.bankoperation.Service;

import com.example.personmicroservice.bankoperation.Model.Transaction;
import com.example.personmicroservice.bankoperation.Model.Transfer;
import com.example.personmicroservice.bankoperation.Model.TransferRequest;
import com.example.personmicroservice.bankoperation.Repositories.TransactionRepository;
import com.example.personmicroservice.bankoperation.Repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private TransferRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transfer save(Transfer account) {
        accountRepository.save(account);
        return accountRepository.findByAccountNumberEquals(account.getAccountNumber());
    }

    @Override
    public List<Transfer> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Transfer findAccountById(Long id) {
        return accountRepository.findAccountById(id);
    }

    @Override
    public Transaction sendMoney(TransferRequest transferRequest) {
        String fromAccountNumber = transferRequest.getFromAccountNumber();
        String toAccountNumber = transferRequest.getToAccountNumber();
        BigDecimal amount = transferRequest.getAmount();
        
        Transfer fromAccount = accountRepository.findByAccountNumberEquals(fromAccountNumber);
        Transfer toAccount = accountRepository.findByAccountNumberEquals(toAccountNumber);
        
        if (fromAccount.getCurrentAmount().compareTo(BigDecimal.ONE) == 1
                && fromAccount.getCurrentAmount().compareTo(amount) == 1) {
            fromAccount.setCurrentAmount(fromAccount.getCurrentAmount().subtract(amount));
            
            accountRepository.save(fromAccount);
            
            toAccount.setCurrentAmount(toAccount.getCurrentAmount().add(amount));
            
            accountRepository.save(toAccount);
            
            Transaction transaction = transactionRepository.save(new Transaction(0L, fromAccountNumber, amount));

            return transaction;
        }

        return null;
    }
}
