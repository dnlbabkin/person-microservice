package com.example.personmicroservice.bankservice.Services;

import com.example.personmicroservice.bankservice.Entity.Person;
import com.example.personmicroservice.bankservice.Repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person savePerson(Person person){
        return personRepository.save(person);
    }

    public Person findPersonById(Integer id){
        return personRepository.findPersonById(id);
    }
}


