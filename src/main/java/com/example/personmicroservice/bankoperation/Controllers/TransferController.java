package com.example.personmicroservice.bankoperation.Controllers;

import com.example.personmicroservice.bankoperation.Entity.Transaction;
import com.example.personmicroservice.bankoperation.DTO.TransferRequest;
import com.example.personmicroservice.bankoperation.Service.AccountService;
import com.example.personmicroservice.bankservice.Clients.SoapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final AccountService accountService;

    @Autowired
    public TransferController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/make-transfer")
    public Transaction makeTransfer(@RequestBody TransferRequest transferRequest) throws JAXBException {
        return accountService.sendMoney(transferRequest);
    }
}
