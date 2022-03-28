package com.example.personmicroservice.bankoperation.Service;

import com.example.personmicroservice.bankoperation.Model.Transfer;
import com.example.personmicroservice.bankoperation.Repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    public BigDecimal getBalance(Integer accountId) {
        BigDecimal balance = transferRepository.getBalanceForId(accountId);

        if(balance == null) {
            throw new IllegalArgumentException();
        }

        return balance;
    }

    public BigDecimal addMoney(Integer to, BigDecimal amount) {
        BigDecimal currentBalance = transferRepository.getBalanceForId(to);

        if(currentBalance == null) {
            transferRepository.save(to, amount);

            return amount;
        } else {
            BigDecimal updatedBalance = currentBalance.add(amount);
            transferRepository.save(to, updatedBalance);

            return updatedBalance;
        }
    }

    public void makeTransfer(Transfer transfer) {
        BigDecimal fromBalance = transferRepository.getBalanceForId(transfer.getFrom());
        BigDecimal toBalance = transferRepository.getBalanceForId(transfer.getTo());

        if(fromBalance == null || toBalance == null) {
            throw new IllegalArgumentException("no id's");
        }

        if(transfer.getAmount().compareTo(fromBalance) > 0){
            throw new IllegalArgumentException("no money");
        }

        BigDecimal updatedFromBalance = fromBalance.subtract(transfer.getAmount());
        BigDecimal updatedToBalance = toBalance.add(transfer.getAmount());

        transferRepository.save(transfer.getFrom(), updatedFromBalance);
        transferRepository.save(transfer.getTo(), updatedToBalance);
    }
}
