package com.example.personmicroservice.Entity;

import com.example.personmicroservice.Entity.Person;
import com.example.personmicroservice.Entity.PersonAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonInfo {

    private Person person;
    private PersonAccount personAccount;
}
