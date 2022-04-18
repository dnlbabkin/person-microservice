package com.example.personmicroservice.bankservice.Controllers;

import com.example.personmicroservice.AllData;
import com.example.personmicroservice.bankservice.Clients.CBRClient;
import com.example.personmicroservice.bankservice.Entity.Account;
import com.example.personmicroservice.bankservice.Services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;

@RestController
@RequiredArgsConstructor
public class SoapController {

    private final CBRClient soapClient;
    private final AccountService accountService;


    @PostMapping(value = "/account", produces = {MediaType.APPLICATION_XML_VALUE})
    public AllData.MainIndicatorsVR invokeSoapClient() throws JAXBException {
        AllData.MainIndicatorsVR env = soapClient.getData();
        env.getCurrency().getUSD().getCurs();

        return env;
    }

    @GetMapping(value = "/account/USD/", produces = {MediaType.APPLICATION_XML_VALUE})
    public Account getUSD() throws JAXBException {
        Account account = new Account();

        return accountService.saveUSD(account);
    }
}
