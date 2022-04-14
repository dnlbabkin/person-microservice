package com.example.personmicroservice.bankoperation.Controllers;

import com.example.personmicroservice.Envelope;
import com.example.personmicroservice.bankoperation.Model.Transaction;
import com.example.personmicroservice.bankservice.Entity.PersonAccount;
import com.example.personmicroservice.bankoperation.Model.TransferRequest;
import com.example.personmicroservice.bankoperation.Service.AccountService;
import com.example.personmicroservice.bankservice.Clients.SoapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private SoapClient soapClient;

    @PostMapping("/make-transfer")
    public Transaction makeTransfer(@RequestBody TransferRequest transferRequest) throws JAXBException {
        return accountService.sendMoney(transferRequest);
    }
}
