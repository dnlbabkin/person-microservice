package com.example.personmicroservice.bankservice.Controllers;

import com.example.personmicroservice.bankservice.Entity.Person;
import com.example.personmicroservice.bankservice.Services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;


    @PostMapping("/")
    public Person savePerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @GetMapping("/{id}")
    public Person findPerson(@PathVariable("id") Integer id){
        return personService.findPersonById(id);
    }
}
