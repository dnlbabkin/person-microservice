package com.example.personmicroservice.bankservice.Services;

import com.example.personmicroservice.AllData;
import com.example.personmicroservice.bankservice.Classes.CardNumberGenerator;
import com.example.personmicroservice.bankservice.Clients.CBRClient;
import com.example.personmicroservice.bankservice.DTO.PersonAccountRequest;
import com.example.personmicroservice.bankservice.Repository.PersonAccountRepository;
import com.example.personmicroservice.bankservice.Entity.PersonAccount;
import com.example.personmicroservice.bankservice.Entity.Person;
import com.example.personmicroservice.bankservice.Repository.PersonRepository;
import com.example.personmicroservice.bankservice.DTO.PersonInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonAccountService {

    private final PersonRepository personRepository;
    private final CBRClient soapClient;
    private final PersonAccountRepository transferRepository;
    private final RestTemplate template;

    @Value("${personaccount.url}")
    private String url;

    @Value("${card.number.id}")
    private String cardNumberId;

    public PersonAccount savePersonAccount(PersonAccountRequest personAccountRequest) throws JAXBException {
        PersonAccount personAccount = new PersonAccount();
        String number = new CardNumberGenerator().generate(cardNumberId);

        AllData.MainIndicatorsVR envelope = soapClient.getData();

        BigDecimal usd = envelope.getCurrency().getUSD().getCurs();

        if (personAccountRequest.getInitialCurrency().equals("rub")) {
            personAccount.setAccountNumber(number);
            personAccount.setCurrentCurrency(personAccountRequest.getInitialCurrency());
            personAccount.setCurrentAmount(personAccountRequest.getInitialPayment());

            transferRepository.save(personAccount);
        } else if (personAccountRequest.getInitialCurrency().equals("usd")) {
            personAccount.setAccountNumber(number);
            personAccount.setCurrentCurrency(personAccountRequest.getInitialCurrency());
            personAccount.setCurrentAmount(personAccountRequest.getInitialPayment());

            BigDecimal result = personAccountRequest.getInitialPayment().divide(usd, 2, RoundingMode.HALF_UP);
            personAccount.setCurrentAmount(result);
            transferRepository.save(personAccount);
        }

        return transferRepository.findByAccountNumberEquals(personAccount.getAccountNumber());
    }

    public List<PersonAccount> findAll() {
        return transferRepository.findAll();
    }

    public PersonInfo getPersonAccount(Integer personId) {
        Person person = personRepository.findPersonById(personId);

        PersonAccount transfer = template
                .getForObject(url + person.getId(), PersonAccount.class);

        return new PersonInfo(person, transfer);
    }

    public PersonAccount getAccountById(Long id) {
        return transferRepository.findAccountById(id);
    }
}
