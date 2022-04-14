package com.example.personmicroservice.bankoperation.Service;

import com.example.personmicroservice.bankoperation.Entity.Transaction;
import com.example.personmicroservice.bankoperation.DTO.TransferRequest;

import javax.xml.bind.JAXBException;

public interface AccountService {
    Transaction sendMoney(TransferRequest transferRequest) throws JAXBException;
}
