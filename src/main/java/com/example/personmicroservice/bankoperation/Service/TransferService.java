package com.example.personmicroservice.bankoperation.Service;

import com.example.personmicroservice.bankoperation.Model.Transfer;
import com.example.personmicroservice.bankoperation.Repositories.TransferRepository;
import com.example.personmicroservice.bankoperation.Model.TransferRequest;
import com.example.personmicroservice.bankservice.Entity.PersonAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    public void makeTransfer(TransferRequest transfer) {
        String fromAccountNumber = transfer.getFromAccount();
        String toAccountNumber = transfer.getToAccount();
        String currency = transfer.getCurrency(); //Потом дописать функционал под валюту
        BigDecimal amount = transfer.getAmount();

        Transfer fromAccount = transferRepository.findByAccountNumberEquals(fromAccountNumber);
        Transfer toAccount = transferRepository.findByAccountNumberEquals(toAccountNumber);

        if (fromAccount.getCurrentAmount().compareTo(BigDecimal.ONE) == 1
        && fromAccount.getCurrentAmount().compareTo(amount) == 1) {
            fromAccount.setCurrentAmount(fromAccount.getCurrentAmount().subtract(amount));

            transferRepository.save(fromAccount);

            toAccount.setCurrentAmount(toAccount.getCurrentAmount().add(amount));

            transferRepository.save(toAccount);
        }

    }

}
