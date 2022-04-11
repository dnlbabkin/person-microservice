package com.example.personmicroservice.bankservice.Services;

import com.example.personmicroservice.bankservice.Repository.PersonAccountRepository;
import com.example.personmicroservice.bankservice.Entity.PersonAccount;
import com.example.personmicroservice.bankservice.Entity.Person;
import com.example.personmicroservice.bankservice.Repository.PersonRepository;
import com.example.personmicroservice.bankservice.Entity.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PersonAccountService {

    @Autowired
    private PersonRepository personRepository;

    private PersonAccountRepository transferRepository;

    @Autowired
    public PersonAccountService(PersonAccountRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    private static final String URL = "http://localhost:8080/person-account/";

    public PersonAccount savePersonAccount(PersonAccount personAccount) {
        transferRepository.save(personAccount);

        return transferRepository.findByAccountNumberEquals(personAccount.getAccountNumber());
    }

    public List<PersonAccount> findAll() {
        return transferRepository.findAll();
    }

    public PersonInfo getPersonAccount(Integer personId) {
        RestTemplate restTemplate = new RestTemplate();


        Person person = personRepository.findPersonById(personId);

        PersonAccount transfer = restTemplate
                .getForObject(URL + person.getId(), PersonAccount.class);

        return new PersonInfo(person, transfer);
    }

    public PersonAccount getAccountById(Long id) {
        return transferRepository.findAccountById(id);
    }
}
