package com.example.personmicroservice.bankservice.Entity;

import com.example.personmicroservice.bankoperation.Model.Transfer;
import lombok.Value;

@Value
public class PersonInfo {
    Person person;
    Transfer personAccount;
}
