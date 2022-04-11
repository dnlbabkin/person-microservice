package com.example.personmicroservice.bankoperation.Service;

import com.example.personmicroservice.bankoperation.Model.Transaction;
import com.example.personmicroservice.bankoperation.Model.TransferRequest;

public interface AccountService {
    Transaction sendMoney(TransferRequest transferRequest);
}
