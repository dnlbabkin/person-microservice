package com.example.personmicroservice.bankservice.Services;

import com.example.personmicroservice.Envelope;
import com.example.personmicroservice.bankservice.Classes.CardNumberGenerator;
import com.example.personmicroservice.bankservice.Clients.SoapClient;
import com.example.personmicroservice.bankservice.DTO.PersonAccountRequest;
import com.example.personmicroservice.bankservice.Repository.PersonAccountRepository;
import com.example.personmicroservice.bankservice.Entity.PersonAccount;
import com.example.personmicroservice.bankservice.Entity.Person;
import com.example.personmicroservice.bankservice.Repository.PersonRepository;
import com.example.personmicroservice.bankservice.DTO.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class PersonAccountService {

    private final PersonRepository personRepository;
    private final SoapClient soapClient;
    private final PersonAccountRepository transferRepository;

    @Autowired
    public PersonAccountService(PersonRepository personRepository, SoapClient soapClient, PersonAccountRepository transferRepository) {
        this.personRepository = personRepository;
        this.soapClient = soapClient;
        this.transferRepository = transferRepository;
    }

    public RestTemplate getTemplate(){
        return new RestTemplate();
    }

    @Value("${personaccount.url}")
    private String url;

    public PersonAccount savePersonAccount(PersonAccountRequest personAccountRequest) throws JAXBException {
        PersonAccount personAccount = new PersonAccount();
        String masterCard = "2212";
        String number = new CardNumberGenerator().generate(masterCard);

        personAccount.setAccountNumber(number);
        personAccount.setCurrentCurrency(personAccountRequest.getInitialCurrency());
        personAccount.setCurrentAmount(personAccountRequest.getInitialPayment());

        Envelope envelope = soapClient.getData();

        BigDecimal usd = envelope.getBody().getAllDataInfoXMLResponse()
                .getAllDataInfoXMLResult().getAllData()
                .getMainIndicatorsVR().getCurrency()
                .getUSD().getCurs();

        if(personAccountRequest.getInitialCurrency().equals("rub")){
            transferRepository.save(personAccount);
        } else if(personAccountRequest.getInitialCurrency().equals("usd")) {
            BigDecimal result = personAccountRequest.getInitialPayment().divide(usd, 2, RoundingMode.HALF_UP);
            personAccountRequest.setInitialPayment(result);
            transferRepository.save(personAccount);
        }

        return transferRepository.findByAccountNumberEquals(personAccount.getAccountNumber());
    }

    public List<PersonAccount> findAll() {
        return transferRepository.findAll();
    }

    public PersonInfo getPersonAccount(Integer personId) {
        Person person = personRepository.findPersonById(personId);

        PersonAccount transfer = getTemplate()
                .getForObject(url + person.getId(), PersonAccount.class);

        return new PersonInfo(person, transfer);
    }

    public PersonAccount getAccountById(Long id) {
        return transferRepository.findAccountById(id);
    }
}
