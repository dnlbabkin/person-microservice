package com.example.personmicroservice.bankservice.Entity;

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
