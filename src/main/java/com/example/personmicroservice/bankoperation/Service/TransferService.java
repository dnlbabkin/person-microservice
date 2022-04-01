package com.example.personmicroservice.bankoperation.Service;

import com.example.personmicroservice.bankoperation.Repositories.TransferRepository;
import com.example.personmicroservice.bankoperation.Model.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    public void makeTransfer(TransferRequest transfer) {
//        transfer.getFromAccount();
//        transfer.getToAccount();
//
//        BigDecimal updateFrom = from.subtract(transfer.getAmount());
//        BigDecimal updateTo = to.add(transfer.getAmount());
//
//        Map<Long, BigDecimal> balanceFrom = new HashMap<>();
//        balanceFrom.put(transfer.getFromAccount(), updateFrom);
//
//        Map<Long, BigDecimal> balanceTo = new HashMap<>();
//        balanceTo.put(transfer.getToAccount(), updateTo);
//        TransferRequest t = new TransferRequest();
//        transferRepository.getBalanceById(1)
//        transferRepository.saveBalance(balanceTo);
    }

//    public Transfer add(Integer to_account) {
//        transferRepository.findById(to_account);
//        return transferRepository.save(to_account);
//    }
}
