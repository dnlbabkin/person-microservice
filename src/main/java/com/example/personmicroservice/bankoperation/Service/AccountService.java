package com.example.personmicroservice.bankoperation.Service;

import com.example.personmicroservice.bankoperation.Model.Transaction;
import com.example.personmicroservice.bankoperation.Model.Transfer;
import com.example.personmicroservice.bankoperation.Model.TransferRequest;

import java.util.List;

public interface AccountService {

    List<Transfer> findAll();

    Transfer save(Transfer account);

    Transaction sendMoney(TransferRequest transferRequest);

    Transfer findAccountById(Long id);
}
