package com.example.personmicroservice.person_microservice;

import com.example.personmicroservice.account.accountdata.Account;
import com.example.personmicroservice.personaccount.PersonAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person savePerson(Person person){
        return personRepository.save(person);
    }

    public Person findPersonById(Integer id){
        return personRepository.findPersonById(id);
    }
}


