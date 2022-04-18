package com.example.personmicroservice.bankoperation.Controllers;

import com.example.personmicroservice.bankoperation.Entity.Transaction;
import com.example.personmicroservice.bankoperation.DTO.TransferRequest;
import com.example.personmicroservice.bankoperation.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final AccountService accountService;

    @PostMapping("/make-transfer")
    public Transaction makeTransfer(@RequestBody TransferRequest transferRequest) throws JAXBException {
        return accountService.sendMoney(transferRequest);
    }
}
