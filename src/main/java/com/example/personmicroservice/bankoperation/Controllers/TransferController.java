package com.example.personmicroservice.bankoperation.Controllers;

import com.example.personmicroservice.bankoperation.Model.Transfer;
import com.example.personmicroservice.bankoperation.Service.TransferService;
import com.example.personmicroservice.bankoperation.Model.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public TransferRequest transfer(@RequestBody TransferRequest transfer) {
        transfer.getFromAccount().getAccount();

        return transfer;
    }

    @PostMapping("/add-account-number")
    public Transfer addAccountNumber(@RequestBody Transfer transfer) {
        return null;
    }
}
