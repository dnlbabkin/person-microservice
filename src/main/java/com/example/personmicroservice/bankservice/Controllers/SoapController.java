package com.example.personmicroservice.bankservice.Controllers;

import com.example.personmicroservice.Envelope;
import com.example.personmicroservice.bankservice.Clients.SoapClient;
import com.example.personmicroservice.bankservice.Entity.Account;
import com.example.personmicroservice.bankservice.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;

@RestController
public class SoapController {

    private final SoapClient soapClient;
    private final AccountService accountService;

    @Autowired
    public SoapController(SoapClient soapClient, AccountService accountService) {
        this.soapClient = soapClient;
        this.accountService = accountService;
    }

    @PostMapping(value = "/account", produces = {MediaType.APPLICATION_XML_VALUE})
    public Envelope invokeSoapClient() throws JAXBException {
        Envelope env = soapClient.getData();
        env.getBody().getAllDataInfoXMLResponse()
                .getAllDataInfoXMLResult();

        return env;
    }

    @GetMapping(value = "/account/USD/", produces = {MediaType.APPLICATION_XML_VALUE})
    public Account getUSD() throws JAXBException {
        Account account = new Account();

        return accountService.saveUSD(account);
    }
}
