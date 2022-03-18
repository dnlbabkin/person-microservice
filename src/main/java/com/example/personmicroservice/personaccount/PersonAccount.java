package com.example.personmicroservice.personaccount;

import com.example.personmicroservice.account.accountdata.Account;
import com.example.personmicroservice.person_microservice.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonAccount {

    private Person person;
    private Account account;
}
