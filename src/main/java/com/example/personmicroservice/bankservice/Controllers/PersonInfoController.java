package com.example.personmicroservice.bankservice.Controllers;

import com.example.personmicroservice.bankservice.Services.PersonAccountService;
import com.example.personmicroservice.bankservice.DTO.PersonInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
public class PersonInfoController {

    private final PersonAccountService personAccountService;


    @GetMapping("/{id}")
    public PersonInfo getPersonAccount(@PathVariable("id") Integer personId) {
        return personAccountService.getPersonAccount(personId);
    }
}
