package com.example.personmicroservice.bankservice.Services;

import com.example.personmicroservice.bankoperation.Model.Transfer;
import com.example.personmicroservice.bankservice.Entity.Person;
import com.example.personmicroservice.bankservice.Repository.PersonRepository;
import com.example.personmicroservice.bankservice.Entity.PersonAccount;
import com.example.personmicroservice.bankservice.Repository.PersonAccountRepository;
import com.example.personmicroservice.bankservice.Entity.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersonAccountService {

    @Autowired
    private PersonAccountRepository personAccountRepository;

    @Autowired
    private PersonRepository personRepository;

    private static final String URL = "http://localhost:8080/transfer/";

    public PersonAccount savePersonAccount(PersonAccount personAccount) {
        return personAccountRepository.save(personAccount);
    }

    public PersonInfo getPersonAccount(Integer personId) {
        RestTemplate restTemplate = new RestTemplate();


        Person person = personRepository.findPersonById(personId);

        Transfer transfer = restTemplate
                .getForObject(URL + person.getId(), Transfer.class);

        return new PersonInfo(person, transfer);
    }

    public PersonAccount getAccountById(Long id) {
        return personAccountRepository.findAccountById(id);
    }
}
