package com.example.personmicroservice.bankservice.Controllers;

import com.example.personmicroservice.Envelope;
import com.example.personmicroservice.bankservice.Clients.SoapClient;
import com.example.personmicroservice.bankservice.Entity.PersonAccount;
import com.example.personmicroservice.bankservice.Entity.PersonAccountRequest;
import com.example.personmicroservice.bankservice.Services.PersonService;
import com.example.personmicroservice.bankservice.Services.PersonAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RestController
@RequestMapping("/person-account")
public class PersonAccountController {

    @Autowired
    private PersonService personService;

    @Autowired
    private SoapClient soapClient;

    @Autowired
    private PersonAccountService personAccountService;

    @PostMapping(value = "/")
    public List<PersonAccount> setAccount(@RequestBody PersonAccountRequest account) throws JAXBException {
        Envelope envelope = soapClient.getData();

        BigDecimal usd = envelope.getBody().getAllDataInfoXMLResponse()
                .getAllDataInfoXMLResult().getAllData()
                .getMainIndicatorsVR().getCurrency()
                .getUSD().getCurs();

        if(account.getInitialCurrency().equals("rub")){
            personAccountService.savePersonAccount(account);
        } else if(account.getInitialCurrency().equals("usd")) {
            BigDecimal result = account.getInitialPayment().divide(usd, 2, RoundingMode.HALF_UP);
            account.setInitialPayment(result);
            personAccountService.savePersonAccount(account);
        }

        return personAccountService.findAll();
    }

    @GetMapping("all-accounts")
    public List<PersonAccount> getAllAccounts(){
        return personAccountService.findAll();
    }

    @GetMapping("/{id}")
    public PersonAccount getAccountById(@PathVariable("id") Long id) {
        return personAccountService.getAccountById(id);
    }
}
 