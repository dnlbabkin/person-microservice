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
        Envelope envelope = soapClient.getData();

        BigDecimal usd = envelope.getBody().getAllDataInfoXMLResponse()
                .getAllDataInfoXMLResult().getAllData().getMainIndicatorsVR()
                .getCurrency().getUSD().getCurs();

        PersonAccount transfer = new PersonAccount();

        if (transferRequest.getCurrency().equals(transfer.getCurrentCurrency())) {
           accountService.sendMoney(transferRequest);
        }

//        if (transferRequest.getCurrency().equals("rub")) {
//            BigDecimal resultRub = transferRequest.getAmount().multiply(usd);
//            transfer.setCurrentAmount(resultRub);
//
//            accountService.sendMoney(transferRequest);
//        } else {
//            BigDecimal resultUsd = transferRequest.getAmount().divide(usd, 2, RoundingMode.HALF_UP);
//            transfer.setCurrentAmount(resultUsd);
//
//            accountService.sendMoney(transferRequest);
//        }

        return accountService.sendMoney(transferRequest);
    }
}
