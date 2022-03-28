package com.example.personmicroservice.bankservice.Services;

import com.example.personmicroservice.bankservice.Entity.Person;
import com.example.personmicroservice.bankservice.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


