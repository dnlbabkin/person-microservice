package com.example.personmicroservice.bankservice.DTO;

import com.example.personmicroservice.bankservice.Entity.Person;
import com.example.personmicroservice.bankservice.Entity.PersonAccount;
import lombok.Value;

@Value
public class PersonInfo {
    Person person;
    PersonAccount personAccount;
}
