package com.example.personmicroservice.bankoperation.Service;

import com.example.personmicroservice.bankoperation.Model.Transaction;
import com.example.personmicroservice.bankoperation.Model.TransferRequest;

import javax.xml.bind.JAXBException;

public interface AccountService {
    Transaction sendMoney(TransferRequest transferRequest) throws JAXBException;
}
