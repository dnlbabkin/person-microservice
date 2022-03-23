package com.example.personmicroservice.Controllers;

import com.example.personmicroservice.Envelope;
import com.example.personmicroservice.Clients.SoapClient;
import com.example.personmicroservice.Services.PersonService;
import com.example.personmicroservice.Entity.PersonAccount;
import com.example.personmicroservice.Services.PersonAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
    public PersonAccount setAccount(@RequestBody PersonAccount account) throws JAXBException {
        Envelope envelope = soapClient.getData();

        BigDecimal usd = envelope.getBody().getAllDataInfoXMLResponse()
                .getAllDataInfoXMLResult().getAllData()
                .getMainIndicatorsVR().getCurrency()
                .getUSD().getCurs();

        BigDecimal result = account.getRUB().divide(usd, 2, RoundingMode.HALF_UP);
        account.setUSD(result);

        personAccountService.savePersonAccount(account);

        return account;
    }

    @GetMapping("/{id}")
    public PersonAccount getAccountById(@PathVariable("id") Integer id) {
        return personAccountService.getAccountById(id);
    }
}
 