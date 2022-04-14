package com.example.personmicroservice.bankservice.Controllers;

import com.example.personmicroservice.bankservice.Entity.PersonAccount;
import com.example.personmicroservice.bankservice.DTO.PersonAccountRequest;
import com.example.personmicroservice.bankservice.Services.PersonAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.util.List;

@RestController
@RequestMapping("/person-account")
public class PersonAccountController {

    private final PersonAccountService personAccountService;

    @Autowired
    public PersonAccountController(PersonAccountService personAccountService) {
        this.personAccountService = personAccountService;
    }

    @PostMapping(value = "/")
    public List<PersonAccount> setAccount(@RequestBody PersonAccountRequest account) throws JAXBException {
        personAccountService.savePersonAccount(account);

        return personAccountService.findAll();
    }


    @GetMapping("all-accounts")
    public List<PersonAccount> getAllAccounts(){
        return personAccountService.findAll();
    }

    @GetMapping("/{id}")
    public PersonAccount getAccountById(@PathVariable("id") Long id) {
        return personAccountService.getAccountById(id);
    }
}
 