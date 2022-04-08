package com.example.personmicroservice.bankoperation.Controllers;

import com.example.personmicroservice.Envelope;
import com.example.personmicroservice.bankoperation.Model.Transaction;
import com.example.personmicroservice.bankoperation.Model.Transfer;
import com.example.personmicroservice.bankoperation.Model.TransferRequest;
import com.example.personmicroservice.bankoperation.Service.AccountService;
import com.example.personmicroservice.bankservice.Clients.SoapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private SoapClient soapClient;

    @PostMapping("/create-account")
    public List<Transfer> createAccount(@RequestBody Transfer account) throws JAXBException {
        Envelope envelope = soapClient.getData();

        BigDecimal usd = envelope.getBody().getAllDataInfoXMLResponse()
                .getAllDataInfoXMLResult().getAllData()
                .getMainIndicatorsVR().getCurrency()
                .getUSD().getCurs();

        if(account.getCurrentCurrency().equals("rub")){
            accountService.save(account);
        } else if(account.getCurrentCurrency().equals("usd")) {
            BigDecimal result = account.getCurrentAmount().divide(usd, 2, RoundingMode.HALF_UP);
            account.setCurrentAmount(result);
            accountService.save(account);
        }

        return accountService.findAll();
    }

    @GetMapping("/accounts")
    public List<Transfer> accounts() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Transfer findAccountById(@PathVariable Long id) {
        return accountService.findAccountById(id);
    }

    @PostMapping("/make-transfer")
    public Transaction makeTransfer(@RequestBody TransferRequest transferRequest) throws JAXBException {
        Envelope envelope = soapClient.getData();

        BigDecimal usd = soapClient.getData().getBody().getAllDataInfoXMLResponse()
                .getAllDataInfoXMLResult().getAllData().getMainIndicatorsVR()
                .getCurrency().getUSD().getCurs();

        Transfer transfer = new Transfer();

        if (transferRequest.getCurrency().equals(transfer.getCurrentAmount())) {
           accountService.sendMoney(transferRequest);
        } else if (transferRequest.getCurrency().equals("rub")) {
            BigDecimal resultRub = transferRequest.getAmount().multiply(usd);
            transfer.setCurrentAmount(resultRub);

            accountService.sendMoney(transferRequest);
        } else if (transferRequest.getCurrency().equals("usd")) {
            BigDecimal resultUsd = transferRequest.getAmount().divide(usd, 2, RoundingMode.HALF_UP);
            transfer.setCurrentAmount(resultUsd);

            accountService.sendMoney(transferRequest);
        }

        return accountService.sendMoney(transferRequest);
    }
}
