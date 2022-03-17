package com.example.personmicroservice.account;

import com.example.personmicroservice.Envelope;
import com.example.personmicroservice.account.accountdata.Account;
import com.example.personmicroservice.account.accountdata.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;

@RestController
public class SoapController {

    @Autowired
    private SoapClient soapClient;

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/account", produces = {MediaType.APPLICATION_XML_VALUE})
    public Envelope invokeSoapClient() throws JAXBException {
        Envelope env = soapClient.getData();
        env.getBody().getAllDataInfoXMLResponse()
                .getAllDataInfoXMLResult();

        return env;
    }

    @GetMapping(value = "/account/USD", produces = {MediaType.APPLICATION_XML_VALUE})
    public String getUSD() throws JAXBException {
        Envelope envelope = soapClient.getData();

//        Account usd = new Account();
//        usd.getUSD();
//
//        usd = envelope.getBody().getAllDataInfoXMLResponse()
//                .getAllDataInfoXMLResult().getAllData()
//                .getMainIndicatorsVR().getCurrency()
//                .getUSD().getCurs();
//
//        accountService.saveUSD(usd);

        return "Курс доллара состовляет: " + envelope.getBody().getAllDataInfoXMLResponse()
                .getAllDataInfoXMLResult().getAllData()
                .getMainIndicatorsVR().getCurrency()
                .getUSD().getCurs() + " рублей";
    }
}
