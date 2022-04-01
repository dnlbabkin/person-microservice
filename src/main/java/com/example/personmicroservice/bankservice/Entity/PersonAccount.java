package com.example.personmicroservice.bankservice.Entity;

import lombok.Setter;
import lombok.Value;

import javax.persistence.*;

@Value
@Table(name = "person_account")
public class PersonAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String account;

    @Embedded
    Income income;
}
