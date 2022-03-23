package com.example.personmicroservice.Services;

import com.example.personmicroservice.Entity.Person;
import com.example.personmicroservice.Repository.PersonRepository;
import com.example.personmicroservice.Entity.PersonAccount;
import com.example.personmicroservice.Repository.PersonAccountRepository;
import com.example.personmicroservice.Entity.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersonAccountService {

    @Autowired
    private PersonAccountRepository personAccountRepository;

    @Autowired
    private PersonRepository personRepository;

    private static final String URL = "http://localhost:8080/person-account/";

    public PersonAccount savePersonAccount(PersonAccount personAccount) {
        return personAccountRepository.save(personAccount);
    }

    public PersonInfo getPersonAccount(Integer personId) {
        RestTemplate restTemplate = new RestTemplate();

        PersonInfo personInfo = new PersonInfo();
        Person person = personRepository.findPersonById(personId);

        PersonAccount personAccount = restTemplate
                .getForObject(URL + person.getId(), PersonAccount.class);

        personInfo.setPerson(person);
        personInfo.setPersonAccount(personAccount);

        return personInfo;
    }

    public PersonAccount getAccountById(Integer id) {
        return personAccountRepository.findAccountById(id);
    }
}
