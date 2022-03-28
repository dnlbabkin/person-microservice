package com.example.personmicroservice.bankoperation.Controllers;

import com.example.personmicroservice.bankoperation.Model.Transfer;
import com.example.personmicroservice.bankoperation.Service.TransferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/balance")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/transfer")
    public void transfer(@RequestBody Transfer transfer) {
        transferService.makeTransfer(transfer);
    }

    @GetMapping("/{accountId}")
    public BigDecimal getBalance(@PathVariable Integer accountId) {
        return transferService.getBalance(accountId);
    }

    @PostMapping("/add")
    public BigDecimal addMoney(@RequestBody Transfer transfer) {
        return transferService.addMoney(transfer.getTo(), transfer.getAmount());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e) {
        log.error(e.getMessage());

        return "Incorrect arguments were entered, " +
                "double-check your entry";
    }
}
