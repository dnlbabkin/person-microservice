package com.example.personmicroservice.personaccount;

import com.example.personmicroservice.person_microservice.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person-account")
public class PersonAccountController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    public PersonAccount getPersonAccount(@PathVariable("id") Integer personId) {
        //TODO сделать вывод пользователя по айди с его счетом
        return null;
    }
}
