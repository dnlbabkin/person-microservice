package com.example.personmicroservice.bankoperation.Controllers;

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
    public void transfer(@RequestBody TransferRequest transfer) {

        transferService.makeTransfer(transfer);
    }

    @PostMapping("/add-account-number")
    public TransferRequest addAccountNumber(@RequestBody TransferRequest transfer) {
        return null;
    }
}
