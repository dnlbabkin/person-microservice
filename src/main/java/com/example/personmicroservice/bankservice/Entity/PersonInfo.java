package com.example.personmicroservice.bankservice.Entity;

import lombok.Value;

@Value
public class PersonInfo {
    Person person;
    PersonAccount personAccount;
}
