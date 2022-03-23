package com.example.personmicroservice.Controllers;

import com.example.personmicroservice.Services.PersonAccountService;
import com.example.personmicroservice.Entity.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class PersonInfoController {

    @Autowired
    private PersonAccountService personAccountService;

    @GetMapping("/{id}")
    public PersonInfo getPersonAccount(@PathVariable("id") Integer personId) {
        return personAccountService.getPersonAccount(personId);
    }
}
